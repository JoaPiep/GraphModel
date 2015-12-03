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
class ClassNode extends Node {

    private List<MethodNode> methodNodes = new ArrayList<>();
    private List<VariableNode> variableNodes = new ArrayList<>();

    private List<String> variableNames = new ArrayList<>();
    private List<String> methodNames = new ArrayList<>();

    private String packageName;

    /**
     *
     * @param name class name
     */
    public ClassNode(String name, String packageName) {
        this.name = name;
        this.packageName = packageName;
    }

    /**
     *
     * @param name class name
     * @param methodNodes list of method nodes
     * @param variableNodes list of variable nodes
     */
    public ClassNode(String name, String packageName, List<MethodNode> methodNodes, List<VariableNode> variableNodes) {
        this.name = name;
        this.packageName = packageName;
        this.methodNodes = methodNodes;
        this.variableNodes = variableNodes;

        if (!variableNodes.isEmpty()) {
            variableNodes.stream().forEach((variableNode) -> {
                variableNames.add(variableNode.getName());
                variableNode.setParentNode(this);
            });
        }
        if (!methodNodes.isEmpty()) {
            methodNodes.stream().forEach((methodNode) -> {
                methodNames.add(methodNode.getName());
                methodNode.setParentNode(this);
            });
        }
    }

    /**
     *
     * @param variable variable to add in the list of variable nodes
     */
    public void addVariable(VariableNode variable) {
        variableNodes.add(variable);
        variableNames.add(variable.getName());
        variable.setParentNode(this);
    }

    /**
     *
     * @param variable variable to add in the list of variable nodes on position
     * i
     * @param i positon in the list of variable nodes
     */
    public void addVariable(VariableNode variable, int i) {
        if (i > -1 && i < variableNodes.size()) {
            variableNodes.add(i, variable);
            variableNames.add(i, variable.getName());
            variable.setParentNode(this);
        } else {
            System.out.println("Choose a position between 0 and " + (variableNodes.size() - 1));
        }
    }

    /**
     *
     * @param variable variable to remove from the list of variable nodes
     */
    public void removeVariable(VariableNode variable) {
        variableNodes.remove(variable);
        variableNames.remove(variable.getName());
        variable.removeParentNode();
    }

    /**
     *
     * @param i position from variable to remove from the list of variable nodes
     */
    public void removeVariable(int i) {
        if (i > -1 && i < variableNodes.size()) {
            variableNodes.get(i).removeParentNode();
            variableNodes.remove(i);
            variableNames.remove(i);
        } else {
            System.out.println("Choose a position between 0 and " + (variableNodes.size() - 1));
        }
    }

    /**
     *
     * @param method method to add in the list of method nodes
     */
    public void addMethod(MethodNode method) {
        methodNodes.add(method);
        methodNames.add(method.getName());
        method.setParentNode(this);
    }

    /**
     *
     * @param method method node to add in the list of method nodes on position
     * i
     * @param i positon in the list of method nodes
     */
    public void addMethod(MethodNode method, int i) {
        if (i > -1 && i < methodNodes.size()) {
            methodNodes.add(i, method);
            methodNames.add(i, method.getName());
            method.setParentNode(this);
        } else {
            System.out.println("Choose a position between 0 and " + (methodNodes.size() - 1));
        }
    }

    /**
     *
     * @param method method to remove from the list of method nodes
     */
    public void removeMethod(MethodNode method) {
        methodNodes.remove(method);
        methodNames.remove(method.getName());
        method.removeParentNode();
    }

    /**
     *
     * @param i position from method to remove from the list of method nodes
     */
    public void removeMethod(int i) {
        if (i > -1 && i < methodNodes.size()) {
            methodNodes.get(i).removeParentNode();
            methodNodes.remove(i);
            methodNames.remove(i);
        } else {
            System.out.println("Choose a position between 0 and " + (methodNodes.size() - 1));
        }
    }

    /**
     *
     * @return list of method nodes
     */
    public List<MethodNode> getMethodNodes() {
        return methodNodes;
    }

    /**
     *
     * @param methodNodes list of method nodes to set
     */
    public void setMethodNodes(List<MethodNode> methodNodes) {
        this.methodNodes = methodNodes;
        methodNames.clear();
        for (MethodNode methodNode : methodNodes) {
            methodNames.add(methodNode.getName());
        }
    }

    /**
     *
     * @return list of variable nodes
     */
    public List<VariableNode> getVariableNodes() {
        return variableNodes;
    }

    /**
     *
     * @param variableNodes list of variable nodes to set
     */
    public void setVariableNodes(List<VariableNode> variableNodes) {
        this.variableNodes = variableNodes;
        variableNames.clear();
        for (VariableNode variableNode : variableNodes) {
            variableNames.add(variableNode.getName());
        }
    }

    /**
     *
     * @return list of variable names
     */
    public List<String> getVariableNames() {
        return variableNames;
    }

    /**
     *
     * @param variableNames list of variable names to set
     */
    public void setVariableNames(List<String> variableNames) {
        this.variableNames = variableNames;
    }

    /**
     *
     * @return list of method names
     */
    public List<String> getMethodNames() {
        return methodNames;
    }

    /**
     *
     * @param methodNames list of method names to set
     */
    public void setMethodNames(List<String> methodNames) {
        this.methodNames = methodNames;
    }

    @Override
    public String toString() {
        return "ClassNode{" + "variableNames=" + variableNames + ", methodNames=" + methodNames + '}';
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

}
