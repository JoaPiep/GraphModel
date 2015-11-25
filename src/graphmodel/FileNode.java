/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphmodel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joanna Pieper <joanna.pieper@gcsc.uni-frankfurt.de>
 */
class FileNode extends Node {

    private List<ClassNode> classNodes = new ArrayList<>();
    private List<String> classNames = new ArrayList<>();

    /**
     *
     * @param name file's name
     */
    public FileNode(String name) {
        this.name = name;
    }

    /**
     *
     * @param name file's name
     * @param classNodes list of class nodes
     */
    public FileNode(String name, List<ClassNode> classNodes) {
        this.name = name;
        this.classNodes = classNodes;

        if (!classNodes.isEmpty()) {
            classNodes.stream().forEach((classNode) -> {
                classNames.add(classNode.getName());
                classNode.setParentNode(this);
            });
        }
    }

    /**
     *
     * @return list of class nodes
     */
    public List<ClassNode> getClassNodes() {
        return classNodes;
    }

    /**
     *
     * @param classNodes list of class nodes to set
     */
    public void setClassNodes(List<ClassNode> classNodes) {
        this.classNodes = classNodes;
        classNames.clear();
        for (ClassNode classNode : classNodes) {
            classNames.add(classNode.getName());
        }
    }

    /**
     *
     * @param classNode class node to add in the list of class nodes
     */
    public void addClass(ClassNode classNode) {
        classNodes.add(classNode);
        classNames.add(classNode.getName());
        classNode.setParentNode(this);
    }

    /**
     *
     * @param classNode class to add in the list of class nodes on position i
     * @param i positon in the list of class nodes
     */
    public void addClass(ClassNode classNode, int i) {
        if (i > -1 && i < classNodes.size()) {
            classNodes.add(i, classNode);
            classNames.add(i, classNode.getName());
            classNode.setParentNode(this);
        } else {
            System.out.println("Choose a position between 0 and " + (classNodes.size() - 1));
        }
    }

    /**
     *
     * @param classNode class node to remove form the list of class nodes
     */
    public void removeClass(ClassNode classNode) {
        classNodes.remove(classNode);
        classNames.remove(classNode.getName());
        classNode.removeParentNode();
    }

    /**
     *
     * @param i position from class node to remove from the list of class nodes
     */
    public void removeClass(int i) {
        if (i > -1 && i < classNodes.size()) {
            classNodes.get(i).removeParentNode();
            classNodes.remove(i);
            classNames.remove(i);
        } else {
            System.out.println("Choose a position between 0 and " + (classNodes.size() - 1));
        }
    }

    /**
     *
     * @return list of class names
     */
    public List<String> getClassNames() {
        return classNames;
    }

    /**
     *
     * @param classNames list of class names to set
     */
    public void setClassNames(List<String> classNames) {
        this.classNames = classNames;
    }

    @Override
    public String toString() {
        return "FileNode{" + "classNames=" + classNames + '}';
    }

}
