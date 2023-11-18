// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyBjgnJtiUc18HAiX3XFPSV38l1qmgnuB7Y",
  authDomain: "traveldatn.firebaseapp.com",
  projectId: "traveldatn",
  storageBucket: "traveldatn.appspot.com",
  messagingSenderId: "356725712158",
  appId: "1:356725712158:web:9864748cfeeb7016a2950f",
  measurementId: "G-TG3B42RGZ7"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const analytics = getAnalytics(app);

import {getStorage} from "firebase/storage";
const storage = getStorage(app);
export {storage}
