/*
 * The idea of computeSimilarityMetric()-function is in the paper:
 * "UMLDiff: An Algorithm for Object-Oriented Design Difference" from
 * Zhenchang Sing and Eleni Stroulia - Universit of Alberta in Canada
 * in Journal Proc. 20th International Conference on Automated Software 
 * Engineering in year 2005
 */
package graphmodel;

import eu.mihosoft.ai.astar.AStar;
import eu.mihosoft.ai.astar.Action;
import eu.mihosoft.ai.astar.WorldDescription;
import eu.mihosoft.ai.astar.stringList.StringList;
import eu.mihosoft.ai.astar.stringList.StringListGoal;
import eu.mihosoft.ai.astar.stringList.StringListHeuristic;
import eu.mihosoft.ai.astar.stringList.StringListState;
import eu.mihosoft.ai.astar.stringList.actions.DeleteAction;
import eu.mihosoft.ai.astar.stringList.actions.IndexAction;
import eu.mihosoft.ai.astar.stringList.actions.InsertAction;
import eu.mihosoft.ai.astar.stringList.actions.RenameAction;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Joanna Pieper <joanna.pieper@gcsc.uni-frankfurt.de>
 */
public class GraphModel {

    static int pow;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        FileGraph fileGraph = generateGraph();

        FileNode sFile = fileGraph.getSourceFile();
        FileNode tFile = fileGraph.getTargetFile();

        if (sFile.getName().equals(tFile.getName())) {
            solveWithAStar(sFile.getClassNames(), tFile.getClassNames());

        }
//        System.out.println("Name similarity " + nameSimilarity(sFile.getName(), tFile.getName()));
//        System.out.println("Structure similarity " + structureSimilarity(tFile.getClassNodes().get(0), sFile.getClassNodes().get(0), "meth"));
        System.out.println("Similarity " + computeSimilarityMetric(tFile.getClassNodes().get(0), sFile.getClassNodes().get(0)));
    }

    /**
     *
     * @return generated graph
     */
    private static FileGraph generateGraph() {

        FileNode fileNode = new FileNode("file");
        FileNode fileNode1 = new FileNode("file1");

        ClassNode cclass1 = new ClassNode("Class1");
        ClassNode cclass2 = new ClassNode("Class2");

        MethodNode meth1 = new MethodNode("meth1");
        MethodNode meth2 = new MethodNode("meth1");
        MethodNode meth3 = new MethodNode("meth3");

        VariableNode v1 = new VariableNode("v1", "String");
        VariableNode v2 = new VariableNode("v2", 6);

        meth1.addVariable(v1);
        meth2.addVariable(v2);

        cclass1.addMethod(meth1);

        cclass2.addMethod(meth2);
        cclass2.addMethod(meth3);

        fileNode.addClass(cclass1);
        fileNode1.addClass(cclass2);

        return new FileGraph(fileNode, fileNode1);
    }

    /**
     *
     * @param sourceMethodList source list of names
     * @param targetMethodList target list of names
     */
    private static void solveWithAStar(List<String> sourceMethodList, List<String> targetMethodList) {

        ArrayList<String> nameList = new ArrayList<String>(new HashSet<String>(targetMethodList));

        IndexAction increaseIndex = new IndexAction("i");
        IndexAction decreaseIndex = new IndexAction("d");
        DeleteAction delete = new DeleteAction();
        RenameAction rename = new RenameAction((ArrayList<String>) targetMethodList);

        ArrayList<Action<StringList>> allActions = new ArrayList<>();

        allActions.add(delete);
        nameList.stream().forEach((name) -> {
            allActions.add(new InsertAction((ArrayList<String>) targetMethodList, name));
        });
        allActions.add(rename);
        allActions.add(increaseIndex);
        allActions.add(decreaseIndex);
        StringList source = new StringList((ArrayList<String>) sourceMethodList);
        StringList target = new StringList((ArrayList<String>) targetMethodList);

        System.out.println("source " + source.getStrings().toString());
        System.out.println("target " + target.getStrings().toString());

        WorldDescription<StringList> StringListWD
                = new WorldDescription<>(new StringListState(source), new StringListGoal(target),
                        allActions, new StringListHeuristic());

        AStar<StringList> solver = new AStar<>(StringListWD);

        solver.run();

        System.out.println("done.");
        System.out.println("");
    }

    /**
     *
     * @param str string
     * @return pairs of adjacent characters from the string
     */
    private static HashSet<String> pairs(String str) {

        String string = str.toUpperCase();

        HashSet<String> pairs = new HashSet();

        for (int i = 0; i < string.length() - 1; i++) {
            String s = "" + string.charAt(i) + string.charAt(i + 1);
            pairs.add(s);
        }
        System.out.println("HashSet " + pairs.toString());

        return pairs;
    }

    /**
     *
     * @param s1 original string
     * @param s2 modified string
     * @return normalized value of name similarity: 1 - strings are identical
     */
    private static double nameSimilarity(String s1, String s2) {

        HashSet<String> pairs1 = pairs(s1);
        HashSet<String> pairs2 = pairs(s2);

        int union = pairs1.size() + pairs2.size();
        pairs1.retainAll(pairs2);
        int intersection = pairs1.size();

        return intersection * 2.0 / union;
    }

    /**
     *
     * @param e1 source node
     * @param e2 target node
     * @param relationType relation type
     * @return normalized value of structure similarint: 1 - nodes have
     * identical structur
     */
    private static double structureSimilarity(Node e1, Node e2, String relationType) {

        System.out.println("Structure Similarity - relation type: " + '"' + relationType + '"');
        double structureSimilarity = 0.0;
        HashSet<Node> e_of_r1 = getEntitiesOfRelation(e1, relationType);
        HashSet<Node> e_of_r2 = getEntitiesOfRelation(e2, relationType);
        System.out.println("e_of_r1 " + e_of_r1.toString());
        System.out.println("e_of_r2 " + e_of_r2.toString());

        if (e_of_r1.isEmpty() && e_of_r2.isEmpty()) {
            pow++;
            structureSimilarity = Math.pow(nameSimilarity(e1.getName(), e2.getName()), pow);
        } else {
            int bevoreCount = 0, afterCount = 0;

            Iterator<Node> i1 = e_of_r1.iterator();
            while (i1.hasNext()) {
                Node er1 = i1.next();
                Iterator<Node> i2 = e_of_r2.iterator();
                while (i2.hasNext()) {
                    Node er2 = i2.next();
                    if (er1.getName().equals(er2.getName())) {
                        bevoreCount += getCount(e1, er1, relationType);
                        afterCount += getCount(e2, er2, relationType);
                        i1.remove();
                        i2.remove();
                    }
                }

            }
            int bevoreLeftCount = 0, afterLeftCount = 0;
            for (Node er1 : e_of_r1) {
                bevoreLeftCount += getCount(e1, er1, relationType);
            }
            for (Node er2 : e_of_r2) {
                afterLeftCount += getCount(e2, er2, relationType);
            }
            int min = Math.min(bevoreCount, afterCount);
            int max = Math.max(bevoreCount, afterCount);
            structureSimilarity = min * 1.0 / (max + bevoreLeftCount + afterLeftCount);

        }
        return structureSimilarity;
    }

    /**
     *
     * @param entity node
     * @param relationType typ of relation
     * @return list of entities related to the entity with the given relation
     * type
     */
    private static HashSet getEntitiesOfRelation(Node entity, String relationType) {

        HashSet<Node> set = new HashSet();
        if (entity instanceof FileNode) {
            if (relationType.equals("class")) {
                FileNode fileNode = (FileNode) entity;
                set = new HashSet(fileNode.getClassNodes());

            }
        } else if (entity instanceof ClassNode) {
            ClassNode classNode = (ClassNode) entity;
            switch (relationType) {
                case "var":
                    set = new HashSet(classNode.getVariableNodes());
                    break;
                case "meth":
                    set = new HashSet(classNode.getMethodNodes());
                    break;
            }
        } else if (entity instanceof MethodNode) {
            if (relationType.equals("var")) {
                MethodNode methodNode = (MethodNode) entity;
                set = new HashSet(methodNode.getVariableNodes());
            }
        }

        return set;
    }

    /**
     *
     * @param e1 main entity
     * @param er1 entity related to entity e1
     * @param relationType the type of relation
     * @return how many times the given type of relation appears between two
     * given entities
     */
    private static int getCount(Node e1, Node er1, String relationType) {

        return 1;
    }

    /**
     *
     * @param e1 source node
     * @param e2 target node
     * @return normalized value of the similarit between the both nodes
     */
    public static double computeSimilarityMetric(Node e1, Node e2) {

        double similarity = 0.0;
        if (e1.getClass().equals(e2.getClass())) {

            List<String> relationTypes = new ArrayList();
            relationTypes.add("class");
            relationTypes.add("meth");
            relationTypes.add("var");

            int N = 1;
            if (e1 instanceof ClassNode) {
                N = 2;
            } else if (e1 instanceof VariableNode) {
                N = 0;
            }

            double nameSimilarity = nameSimilarity(e1.getName(), e2.getName());

            pow = 0;
            double metric = 0.0;

            for (String string : relationTypes) {

                metric += structureSimilarity(e1, e2, string);
                System.out.println("--------------------------------------------");
            }
            similarity = (nameSimilarity + metric) / (nameSimilarity + N);
        }
        return similarity;
    }

}
