package com.example.datn.controller;


import com.example.datn.dto.ContractInfoDTO;
import com.example.datn.service.OrderService;


import com.example.datn.service.SendMailService;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1/docx")
public class WriteDocXController {
    private final ResourceLoader resourceLoader;

    @Autowired
     OrderService orderService;

    @Autowired
    private SendMailService sendMailService;

    public WriteDocXController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
    @GetMapping(value = "/read-docx", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resource> readDocx() {
        List<String> paragraphs = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatHour = new SimpleDateFormat("HH:mm");

        try {
            ContractInfoDTO contractInfoDTO = orderService.sendContract(Long.valueOf(41));
            InputStream inputStream = resourceLoader.getResource("classpath:HD.docx").getInputStream();
            XWPFDocument document = new XWPFDocument(inputStream);

            int numRows = contractInfoDTO.getTouristDTOList().size();

                // Chèn bảng mới vào vị trí chứa chữ "table"
               XWPFTable xwpfTable =  document.getTableArray(0);
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
                    xwpfTable.getRow(row+1).getCell(0).setText(stt.toString());
                    xwpfTable.getRow(row+1).getCell(1).setText(contractInfoDTO.getTouristDTOList().get(row - 1).getFullname());
                    xwpfTable.getRow(row+1).getCell(2).setText(contractInfoDTO.getTouristDTOList().get(row - 1).getGender());
                    xwpfTable.getRow(row+1).getCell(3).setText(format.format(contractInfoDTO.getTouristDTOList().get(row -1).getDob()));
                    xwpfTable.getRow(row+1).getCell(4).setText("");
                    xwpfTable.getRow(row+1).getCell(5).setText("");
                    xwpfTable.getRow(row+1).getCell(6).setText("");
                }


            XWPFTable xwpfTable1 =  document.getTableArray(1);
            xwpfTable1.createRow();
            xwpfTable1.getRow(1).getCell(0).setText("");
            xwpfTable1.getRow(1).getCell(1).setText("Số lượng");
            xwpfTable1.getRow(1).getCell(2).setText("Giá/người");
            xwpfTable1.getRow(1).getCell(3).setText("Ghi chú");
              int numRowsCount = 2;
              if(contractInfoDTO.getAdultCount() != 0){
                  xwpfTable1.createRow();
                  xwpfTable1.getRow(numRowsCount).getCell(0).setText("Người lớn");
                  xwpfTable1.getRow(numRowsCount).getCell(1).setText(contractInfoDTO.getAdultCount().toString());
                  xwpfTable1.getRow(numRowsCount).getCell(2).setText(contractInfoDTO.getAdultPrice().toString());
                  xwpfTable1.getRow(numRowsCount).getCell(3).setText("");
                  numRowsCount++;
              }
            if(contractInfoDTO.getChildrenCount() != 0){
                xwpfTable1.createRow();
                xwpfTable1.getRow(numRowsCount).getCell(0).setText("Trẻ em (4-12 tuổi)");
                xwpfTable1.getRow(numRowsCount).getCell(1).setText(contractInfoDTO.getChildrenCount().toString());
                xwpfTable1.getRow(numRowsCount).getCell(2).setText(contractInfoDTO.getChildrenPrice().toString());
                xwpfTable1.getRow(numRowsCount).getCell(3).setText("");
                numRowsCount++;
            }
            if(contractInfoDTO.getKidCount() != 0){
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
                if(runs != null){
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
                            text = text.replace("ransaction", contractInfoDTO.getOrderCode());
                            run.setText(text, 0);
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
                            text = text.replace("identity", contractInfoDTO.getIdentityCard());
                            run.setText(text, 0);
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
            OutputStream out = new FileOutputStream(new File("output.pdf"));
            PdfConverter.getInstance().convert(documentReadPdf, out, options);;
            out.close();
            documentReadPdf.close();
            doc.close();
            inputStream1.close();

            sendMailService.sendMailAttachment("quochoan.hust.k63@gmail.com","Gửi hợp đồng","","output.pdf");



            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(outputStream.size())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } catch (IOException | MessagingException e) {
            e.printStackTrace();
        }

        return null;
    }


}
