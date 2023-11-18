package com.example.datn.service;

import com.example.datn.dto.ContractInfoDTO;
import com.example.datn.entity.Orders;
import com.example.datn.repository.OrdersRepository;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class WriteDocxService {

    private final ResourceLoader resourceLoader;

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    private SendMailService sendMailService;

    public WriteDocxService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    private String uploadFile(File file, String fileName) throws IOException {
        String bucketName = "traveldatn.appspot.com"; // Replace with your actual bucket name
        BlobId blobId = BlobId.of(bucketName, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("application/pdf").build();
        Credentials credentials = GoogleCredentials.fromStream(resourceLoader.getResource("classpath:traveldatn-private-key.json").getInputStream());
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
        return String.format("https://firebasestorage.googleapis.com/v0/b/%s/o/%s?alt=media", URLEncoder.encode(bucketName, StandardCharsets.UTF_8), URLEncoder.encode(fileName, StandardCharsets.UTF_8));    }


    public String download(String fileName) throws IOException {
        String destFileName = UUID.randomUUID().toString().concat(fileName);
        String destFilePath = "E:\\New folder\\" + destFileName;                                    // to set destination file path

        Credentials credentials = GoogleCredentials.fromStream(resourceLoader.getResource("classpath:traveldatn-private-key.json").getInputStream());
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        Blob blob = storage.get(BlobId.of("traveldatn.appspot.com", fileName));
        blob.downloadTo(Paths.get(destFilePath));
        return "Successfully Downloaded!";
    }
    public void readDocx(Long orderId, ContractInfoDTO contractInfoDTO) throws Exception {
        Optional<Orders> orders = ordersRepository.findById(orderId);
        if(orders.isPresent()){
            List<String> paragraphs = new ArrayList<>();
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat formatHour = new SimpleDateFormat("HH:mm");

                InputStream inputStream = resourceLoader.getResource("classpath:HD.docx").getInputStream();
                XWPFDocument document = new XWPFDocument(inputStream);

                int numRows = contractInfoDTO.getTouristDTOList().size();

                // Chèn bảng mới vào vị trí chứa chữ "table"
                XWPFTable xwpfTable = document.getTableArray(0);
                xwpfTable.createRow();
                xwpfTable.getRow(1).getCell(0).setText("STT");
                xwpfTable.getRow(1).getCell(1).setText("Họ và tên");
                xwpfTable.getRow(1).getCell(2).setText("Giới tính");
                xwpfTable.getRow(1).getCell(3).setText("Ngày sinh");
                xwpfTable.getRow(1).getCell(4).setText("Số điện thoại");
                xwpfTable.getRow(1).getCell(5).setText("Email");
                xwpfTable.getRow(1).getCell(6).setText("Ghi chú");
                for (int row = 1; row < numRows + 1; row++) {
                    xwpfTable.createRow();
                    Integer stt = row;
                    xwpfTable.getRow(row + 1).getCell(0).setText(stt.toString());
                    xwpfTable.getRow(row + 1).getCell(1).setText(contractInfoDTO.getTouristDTOList().get(row - 1).getFullname());
                    xwpfTable.getRow(row + 1).getCell(2).setText(contractInfoDTO.getTouristDTOList().get(row - 1).getGender());
                    xwpfTable.getRow(row + 1).getCell(3).setText(format.format(contractInfoDTO.getTouristDTOList().get(row - 1).getDob()));
                    xwpfTable.getRow(row + 1).getCell(4).setText("");
                    xwpfTable.getRow(row + 1).getCell(5).setText("");
                    xwpfTable.getRow(row + 1).getCell(6).setText("");
                }


                XWPFTable xwpfTable1 = document.getTableArray(1);
                xwpfTable1.createRow();
                xwpfTable1.getRow(1).getCell(0).setText("");
                xwpfTable1.getRow(1).getCell(1).setText("Số lượng");
                xwpfTable1.getRow(1).getCell(2).setText("Giá/người");
                xwpfTable1.getRow(1).getCell(3).setText("Ghi chú");
                int numRowsCount = 2;
                if (contractInfoDTO.getAdultCount() != 0) {
                    xwpfTable1.createRow();
                    xwpfTable1.getRow(numRowsCount).getCell(0).setText("Người lớn");
                    xwpfTable1.getRow(numRowsCount).getCell(1).setText(contractInfoDTO.getAdultCount().toString());
                    xwpfTable1.getRow(numRowsCount).getCell(2).setText(contractInfoDTO.getAdultPrice().toString());
                    xwpfTable1.getRow(numRowsCount).getCell(3).setText("");
                    numRowsCount++;
                }
                if (contractInfoDTO.getChildrenCount() != 0) {
                    xwpfTable1.createRow();
                    xwpfTable1.getRow(numRowsCount).getCell(0).setText("Trẻ em (4-12 tuổi)");
                    xwpfTable1.getRow(numRowsCount).getCell(1).setText(contractInfoDTO.getChildrenCount().toString());
                    xwpfTable1.getRow(numRowsCount).getCell(2).setText(contractInfoDTO.getChildrenPrice().toString());
                    xwpfTable1.getRow(numRowsCount).getCell(3).setText("");
                    numRowsCount++;
                }
                if (contractInfoDTO.getKidCount() != 0) {
                    xwpfTable1.createRow();
                    xwpfTable1.getRow(numRowsCount).getCell(0).setText("Trẻ em dưới 4 tuổi");
                    xwpfTable1.getRow(numRowsCount).getCell(1).setText(contractInfoDTO.getKidCount().toString());
                    xwpfTable1.getRow(numRowsCount).getCell(2).setText(contractInfoDTO.getKidPrice().toString());
                    xwpfTable1.getRow(numRowsCount).getCell(3).setText("");
                    numRowsCount++;
                }


                for (XWPFParagraph paragraph : document.getParagraphs()) {
//                String text = paragraph.getText();
//                paragraphs.add(text);
                    List<XWPFRun> runs = paragraph.getRuns();
                    if (runs != null) {
                        for (XWPFRun run : runs) {
                            String text = run.getText(0);
                            if (text != null && text.contains("dateTime")) {
                                text = text.replace("dateTime", format.format(new Date()));
                                run.setText(text, 0);
                            }
                            if (text != null && text.contains("fullname")) {
                                text = text.replace("fullname", contractInfoDTO.getFullname().toUpperCase(Locale.ROOT));
                                run.setText(text, 0);
                            }
                            if (text != null && text.contains("address")) {
                                text = text.replace("address", contractInfoDTO.getAddress());
                                run.setText(text, 0);
                            }
                            if (text != null && text.contains("phone")) {
                                text = text.replace("phone", contractInfoDTO.getPhoneNumber());
                                run.setText(text, 0);
                            }
                            if (text != null && text.contains("email")) {
                                text = text.replace("email", contractInfoDTO.getEmail());
                                run.setText(text, 0);
                            }

                            if (text != null && text.contains("startDate")) {
                                text = text.replace("startDate", format.format(contractInfoDTO.getStartTime()));
                                run.setText(text, 0);
                            }
                            if (text != null && text.contains("begin")) {
                                text = text.replace("begin", formatHour.format(contractInfoDTO.getStartTime()));
                                run.setText(text, 0);
                            }

                            if (text != null && text.contains("tourName")) {
                                text = text.replace("tourName", contractInfoDTO.getNameTour());
                                run.setText(text, 0);
                            }

                            if (text != null && text.contains("ransaction")) {
                                if(contractInfoDTO.getOrderCode() == null){
                                    text = text.replace("ransaction","");
                                    run.setText(text, 0);
                                } else {
                                    text = text.replace("ransaction", contractInfoDTO.getOrderCode());
                                    run.setText(text, 0);
                                }

                            }
                            if (text != null && text.contains("period")) {
                                text = text.replace("period", contractInfoDTO.getPeriod().toString());
                                run.setText(text, 0);
                            }
                            if (text != null && text.contains("rice")) {
                                text = text.replace("rice", contractInfoDTO.getSumPrice().toString());
                                run.setText(text, 0);
                            }
                            if (text != null && text.contains("identity")) {
                                if(contractInfoDTO.getIdentityCard() == null){
                                    text = text.replace("identity", "");
                                    run.setText(text, 0);
                                } else {
                                    text = text.replace("identity", contractInfoDTO.getIdentityCard());
                                    run.setText(text, 0);
                                }

                            }
                        }

                    }
                }
                FileOutputStream fos = new FileOutputStream("output.docx");
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                document.write(outputStream);
                ByteArrayInputStream inputStream1 = new ByteArrayInputStream(outputStream.toByteArray());


                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=output.docx");
                document.close();

                // Trả về file DOCX dưới dạng InputStreamResource
                Resource resource = new InputStreamResource(inputStream1);
                //
                InputStream doc = new ByteArrayInputStream(outputStream.toByteArray());
                XWPFDocument documentReadPdf = new XWPFDocument(doc);
                PdfOptions options = PdfOptions.create();

                File outputFilePdf = new File("hopdongdulich"+contractInfoDTO.getOrderCode()+contractInfoDTO.getEmail()+".pdf");
                OutputStream out = new FileOutputStream(outputFilePdf);
                PdfConverter.getInstance().convert(documentReadPdf, out, options);
                uploadFile(outputFilePdf,"hopdongdulich"+contractInfoDTO.getOrderCode()+contractInfoDTO.getEmail()+".pdf");
                out.close();
                documentReadPdf.close();
                doc.close();
                inputStream1.close();
                this.download("hopdongdulich"+contractInfoDTO.getOrderCode()+contractInfoDTO.getEmail()+".pdf");

                sendMailService.sendMailAttachment(orders.get().getEmail(), "Công ty lữ hành Lửa Việt gửi hợp đồng", "Hợp đồng du lịch", "hopdongdulich.pdf");


        } else {
            throw new Exception("Đơn đặt hàng không tồn tại!");
        }
    }
}
