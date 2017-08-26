package com.example;

import java.io.*;
import org.gjt.jclasslib.io.ClassFileWriter;
import org.gjt.jclasslib.structures.CPInfo;
import org.gjt.jclasslib.structures.ClassFile;
import org.gjt.jclasslib.structures.constants.ConstantUtf8Info;

/**
 * Created by Administrator on 2017/8/10.
 */
public class Test {
   public static  void main(String arg[]) throws Exception{
       System.out.println("韩梦飞沙");
       String filePath = "D:\\MonitorContentFactory.class";
       FileInputStream fis = new FileInputStream(filePath);

       DataInput di = new DataInputStream(fis);
       ClassFile cf = new ClassFile();
       cf.read(di);
       CPInfo[] infos = cf.getConstantPool();

       int count = infos.length;
       for (int i = 0; i < count; i++) {
           if (infos[i] != null) {
               System.out.print(i);
               System.out.print(" = ");
               System.out.print(infos[i].getVerbose());
               System.out.print(" = ");
               System.out.println(infos[i].getTagVerbose());
               if(i == 70){
                   ConstantUtf8Info uInfo = (ConstantUtf8Info)infos[i];
                   uInfo.setBytes("韩梦飞沙-监视器".getBytes());
                   infos[i]=uInfo;
               }
           }
       }
       cf.setConstantPool(infos);
       fis.close();
       File f = new File(filePath);
       ClassFileWriter.writeToFile(f, cf);
   }
}
