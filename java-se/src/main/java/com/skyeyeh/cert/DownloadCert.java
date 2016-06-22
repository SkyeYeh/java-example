package com.skyeyeh.cert;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 下載工商憑證。
 */
public class DownloadCert {
    private static final String RESOURCE_PATH = "D:\\IdeaProjects\\java-example\\java-se\\src\\main\\resources\\";
    private static final String SERIAL_NOS = RESOURCE_PATH + "serial_nos.txt";
    private static final String CERT_PATH = RESOURCE_PATH + "cert\\";
    private static final String URL_HEAD = "http://moeaca.nat.gov.tw/PEXE_MOEACA/DownLoadCert.CEXE?CertNo=";
    private static final String URL_FOOT = "&DownCert=%A4U%B8%FC";
    private static final int BUFFER_SIZE = 4096;

    /**
     * 從檔案讀取 SerialNos。
     *
     * @return SerialNos
     * @throws IOException 讀取檔案例外
     */
    public List<String> getSerialNos() throws IOException {
        List<String> serialNos = new ArrayList<>();
        File file = new File(SERIAL_NOS);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            // 解析附卡。
            if (line.startsWith("00") && line.length() == 34) {
                line = line.substring(2);
            }
            serialNos.add(line);
        }

        return serialNos;
    }

    /**
     * 下載憑證。
     *
     * @param serialNo serialNo
     * @return 憑證
     * @throws IOException 下載憑證例外
     */
    public File downloadCert(String serialNo) throws IOException {
        File file = null;

        // 連線至工商憑證管理中心。
        URL url = new URL(getUrl(serialNo));
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

        if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            // 取得 HTML 規定。
            String fileName = null;
            String disposition = httpURLConnection.getHeaderField("Content-Disposition");
            if (disposition != null) {
                // 取得檔案名稱。
                fileName = getFileName(disposition);
            } else {
                return null;
            }
            file = new File(CERT_PATH + fileName);

            // 建立讀取二進制檔案串流。
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

            // 建立寫入二進制檔案串流。
            if (file.exists()) {
                file.delete();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

            // 寫入檔案。
            int read;
            int[] result;
            while ((read = bufferedInputStream.read()) != -1) {
                bufferedOutputStream.write(read);
            }

            bufferedOutputStream.close();
            fileOutputStream.close();
            bufferedInputStream.close();
            inputStream.close();
        }

        return file;
    }

    /**
     * 取得工商憑證管理中心網址。
     *
     * @param serialNo 憑證序號。
     * @return 工商憑證管理中心網址
     */
    private String getUrl(String serialNo) {
        return URL_HEAD + serialNo + URL_FOOT;
    }

    /**
     * 取得檔案名稱。
     *
     * @param disposition HTML 規定
     * @return 檔案名稱
     */
    private String getFileName(String disposition) {
        String fileName = null;
        // Content-Disposition: form-data; name="up_file"; filename="D:\local\docs\images\banner.gif"
        int index = disposition.indexOf("filename=");
        if (index > 0) {
            fileName = disposition.substring(index + 10, disposition.length() - 1);
        }
        return fileName;
    }

    public static void main(String[] args) {
        DownloadCert downloadCert = new DownloadCert();

        List<String> serialNos = null;
        try {
            // 從檔案讀取 SerialNos。
            serialNos = downloadCert.getSerialNos();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
//            for (String serialNo : serialNos) {
            String serialNo = "F0D92E63183D41DBC651C99CD1039859";

            // 下載憑證。
            downloadCert.downloadCert(serialNo);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
