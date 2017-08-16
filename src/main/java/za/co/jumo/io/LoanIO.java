/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.jumo.io;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author mase
 */
public class LoanIO {
    
    private File file = null;
    private static final String UTF_ENCODING = "UTF-8";
    
    public LoanIO(String path) throws UnsupportedEncodingException, IOException {
        String decodedPath = URLDecoder.decode(path, UTF_ENCODING);
        file = new File(decodedPath); 
    }
    
    public List<String> read(){
        List<String> lines = null;
        try{
           lines = FileUtils.readLines(file, UTF_ENCODING); 
        } catch (IOException io){
            Logger.getLogger(LoanIO.class.getName()).log(Level.SEVERE, null, io);
        }  
        
        return lines;
    } 
    
    public void write(String line){
        try {
            FileUtils.writeStringToFile(file, line, UTF_ENCODING, true);
        } catch (IOException ex) {
            Logger.getLogger(LoanIO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void clear(String path){
        File file = FileUtils.getFile(path);
        FileUtils.deleteQuietly(file);
    }
}
