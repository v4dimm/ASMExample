package org.example;

import org.objectweb.asm.*;
import org.objectweb.asm.commons.RemappingClassAdapter;
import org.objectweb.asm.commons.AdviceAdapter;

public class NewClassAdaptor extends ClassVisitor {
    public NewClassAdaptor(ClassVisitor cv) {
        super(Opcodes.ASM4, cv);
    }

    @Override
    public MethodVisitor visitMethod(
            int access,
            String name,
            String desc,
            String signature,
            String[] exceptions) {

        MethodVisitor mv;
        mv = cv.visitMethod(access, name, desc, signature, exceptions);
        mv = new NewMethodAdapter(Opcodes.ASM4, mv, access, name, desc);
        return mv;
    }

}
