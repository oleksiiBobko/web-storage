package com.bobko.album.util;

/**
 * @author oleksii bobko
 * @data 12.08.2013
 */

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bobko.album.domain.Picture;

public class PictureGrabber {

//    private static final String PATTERN_STRING = "(<img[^>]*src=[\"']([^\"^']*))()";
    private static final String PATTERN_STRING = "\\(?\\b((https?|ftp|file)://|www[.])[-A-Za-z0-9+&amp;@#/%?=~_()|!:,.;]*[-A-Za-z0-9+&amp;@#/%=~_()|]";

    private String uRL;
    private LinkedList<String> uRLs;
    private Iterator<String> it;
    private String rootDir;
    private String userName;
    private final static int size = 1024;

    public PictureGrabber(String uRL, String rootDir, String userName) {
        this.uRL = uRL;
        this.rootDir = rootDir;
        this.userName = userName;
        createURLList();
        it = uRLs.iterator();
    }

    public Picture getNextPicture() {
        Picture nextPicture = null;
        while (it.hasNext()) {
            String imgUrl = (String) it.next();
            nextPicture = getPicture(imgUrl);
            if (nextPicture != null) {
                return nextPicture;
            }
        }
        return null;
    }

    private void createURLList() {
        InputStreamReader in = null;
        try {
            uRLs = new LinkedList<String>();
            if ((uRL == null) || (uRL.isEmpty())) {
                return;
            } else {
                URL url = new URL(uRL);
                // if it's need to
                // Proxy proxy = new Proxy(Proxy.Type.HTTP, new
                // InetSocketAddress("172.30.0.2", 3128));
                URLConnection connection = url.openConnection();
                InputStream inputStream = connection.getInputStream();
                in = new InputStreamReader(inputStream);

                // read contents into string buffer
                StringBuffer input = new StringBuffer();

                int ch;
                while ((ch = in.read()) != -1) {
                    input.append((char) ch);
                }

                Pattern pattern = Pattern.compile(PATTERN_STRING,
                        Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(input);

                while (matcher.find()) {
                    int start = matcher.start();
                    int end = matcher.end();
                    String match = input.substring(start, end);
                    String[] tmp = match.split("src=");
                    if (tmp.length >= 2) {
                        match = tmp[1].substring(1);
                    }

                    if (match.startsWith("//")) {
                        match = match.substring(1);
                    }

                    if (match.startsWith("/")) {
                        match = getClearAdress(uRL) + match;
                    }

                    uRLs.add(match);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private String getClearAdress(String url) {
        try {
            URI uri = new URI(url);
            String result = uri.getScheme() + "://" + uri.getHost();
            return result.startsWith("www.") ? result.substring(4) : result;
        } catch (URISyntaxException ex) {
            return "";
        }
    }

    private Picture getPicture(String imgUrl) {
        Picture pic = new Picture();
        String fileName = downloadFile(imgUrl, rootDir + "/" + userName + "/");
        if (fileName == null) {
            return null;
        }
        pic.setPath(userName + File.separator + fileName);
        pic.setDescription(getClearAdress(uRL));
        int slashIndex = imgUrl.lastIndexOf('/');
        String originalFileName = imgUrl.substring(slashIndex + 1);
        if ((originalFileName != null) && !originalFileName.isEmpty()) {
            pic.setFilename(originalFileName);
        } else {
            pic.setFilename("imgUrl");
        }
        pic.setOwner(userName);
        return pic;
    }

    private void performDownloading(String fAddress, String localFileName,
            String destinationDir) {
        OutputStream outStream = null;
        HttpURLConnection connection = null;

        InputStream is = null;
        try {
            URL urlToPicture;
            byte[] buf;
            int ByteRead, ByteWritten = 0;
            urlToPicture = new URL(fAddress);
            outStream = new BufferedOutputStream(new FileOutputStream(
                    destinationDir + File.separator + localFileName));

            // Proxy proxy = new Proxy(Proxy.Type.HTTP, new
            // InetSocketAddress("172.30.0.2", 3128));

            connection = (HttpURLConnection) urlToPicture.openConnection();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.println(connection.getErrorStream());
            } else {
                is = connection.getInputStream();
                buf = new byte[size];
                while ((ByteRead = is.read(buf)) != -1) {
                    outStream.write(buf, 0, ByteRead);
                    ByteWritten += ByteRead;
                }
                System.out.println("Downloaded Successfully.");
                System.out.println("File name:\"" + localFileName
                        + "\"\nNo ofbytes :" + ByteWritten);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null)
                    is.close();
                if (outStream != null)
                    outStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String downloadFile(String fAddress, String destinationDir) {
        int slashIndex = fAddress.lastIndexOf('/');
        int periodIndex = fAddress.lastIndexOf('.');

        String suffix = fAddress.substring(periodIndex);

        if (!isValidSuffix(suffix)) {
            suffix = ".null";
        }

        String fileName = AlbumUtils.getUUID() + suffix;

        if (periodIndex >= 1 && slashIndex >= 0
                && slashIndex < fAddress.length() - 1) {
            performDownloading(fAddress, fileName, destinationDir);
        } else {
            System.err.println("path or file name.");
            return null;
        }
        return fileName;
    }

    private boolean isValidSuffix(String suffix) {
        File f = new File(suffix);
        try {
            f.getCanonicalPath();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}