package org.example;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;

public class NewMethodAdapter extends AdviceAdapter {
//    private String name;

    protected NewMethodAdapter(int opcodeAndSource, MethodVisitor methodVisitor, int access, String name, String desc) {
        super(opcodeAndSource, methodVisitor, access, name, desc);
//        this.name = name;
//        this.mv = methodVisitor;
    }

    @Override
    public void visitMethodInsn(int opcodeAndSource, String owner, String name, String description, boolean isInterface) {
        super.visitMethodInsn(opcodeAndSource, owner, name, description, isInterface);
        if(name.equalsIgnoreCase("println")) {
            printNewString("Hi, Hacker Volodya");
        }
    }

    private void printNewString(String string) {
        mv.visitCode();
        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitLdcInsn("------" + string + "-----");
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
        mv.visitEnd();
    }
}
