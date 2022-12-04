package com.redes.redes.Utils;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Component;

@Component
public class WriteFile {

    public void writeFile(String action) throws IOException {

        FileOutputStream fos = new FileOutputStream(
                "src\\main\\java\\com\\redes\\redes\\Logs\\" + Long.toString(System.currentTimeMillis()) + ".txt");
        DataOutputStream outStream = new DataOutputStream(new BufferedOutputStream(fos));
        outStream.writeBytes("Ação: " + action + "\n");
        outStream.close();

    }

}
