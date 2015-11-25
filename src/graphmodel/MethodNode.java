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
class MethodNode extends Node {

    private List<VariableNode> variableNodes = new ArrayList<>();
    private List<String> variableNames = new ArrayList<>();

    /**
     *
     * @param name method's name
     */
    public MethodNode(String name) {
        this.name = name;
    }

    /**
     *
     * @param name method's name
     * @param variableNodes list of variable nodes
     */
    public MethodNode(String name, List<VariableNode> variableNodes) {
        this.name = name;
        this.variableNodes = variableNodes;

        if (!variableNodes.isEmpty()) {
            variableNodes.stream().forEach((variableNode) -> {
                variableNames.add(variableNode.getName());
                variableNode.setParentNode(this);
            });
        }
    }

    /**
     *
     * @param variable variable to add in the variable list
     */
    public void addVariable(VariableNode variable) {
        variableNodes.add(variable);
        variableNames.add(variable.getName());
        variable.setParentNode(this);
    }

    /**
     *
     * @param variable variable to add in the variable list on position i
     * @param i position in the list
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
     * @param i positon of variable to remove from the list of variable nodes
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

    @Override
    public String toString() {
        return "MethodNode{" + "variableNames=" + variableNames + '}';
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
     * @param variableNodes variable nodes to set
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
     * @param variableNames variable names to set
     */
    public void setVariableNames(List<String> variableNames) {
        this.variableNames = variableNames;
    }
}
