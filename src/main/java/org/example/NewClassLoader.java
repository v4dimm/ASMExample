package org.example;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class NewClassLoader extends ClassLoader {

    /*
     * You can use "return" for return bytecode to MainClass
     * Or create new class file with bytecode
     * */

    @Override
    protected Class findClass(String name) throws ClassNotFoundException {
        try {
            FileOutputStream fos = new FileOutputStream("New.class");

            ClassReader cr = new ClassReader(name);
            ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);

            NewClassAdaptor ca = new NewClassAdaptor(cw);

            //begin make new byte code
            cr.accept(ca, ClassReader.EXPAND_FRAMES);

            //new byte code
            byte b[] = cw.toByteArray();

//            return defineClass(name, b, 0, b.length);

            fos.write(b,0, b.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadClass(name);
    }
}
