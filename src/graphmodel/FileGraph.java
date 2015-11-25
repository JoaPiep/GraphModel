/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphmodel;

/**
 *
 * @author Joanna Pieper <joanna.pieper@gcsc.uni-frankfurt.de>
 */
class FileGraph {

    FileNode sourceFile;
    FileNode targetFile;

    /**
     * 
     * @param sourceFile source file    
     * @param targetFile target file
     */
    public FileGraph(FileNode sourceFile, FileNode targetFile) {
        this.sourceFile = sourceFile;
        this.targetFile = targetFile;
    }

    /**
     * 
     * @return source file
     */
    public FileNode getSourceFile() {
        return sourceFile;
    }

    /**
     * 
     * @param sourceFile source file to set
     */
    public void setSourceFile(FileNode sourceFile) {
        this.sourceFile = sourceFile;
    }

    /**
     * 
     * @return target file
     */
    public FileNode getTargetFile() {
        return targetFile;
    }

    /**
     * 
     * @param targetFile target file to set
     */
    public void setTargetFile(FileNode targetFile) {
        this.targetFile = targetFile;
    }

}
