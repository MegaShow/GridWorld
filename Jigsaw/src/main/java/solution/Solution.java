package solution;

import jigsaw.Jigsaw;
import jigsaw.JigsawNode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * A solution of N-Puzzle problem with BFSearch and ASearch.
 */
public class Solution extends Jigsaw {
    private static final int F0W = 20;
    private static final int F1W = 5;
    private static final int F2W = 0;
    private static final int F3W = 7;

    /**
     * Constructor.
     */
    public Solution() {
        // Do nothing
    }

    /**
     * Constructor with begin node and end node.
     *
     * @param bNode begin node
     * @param eNode end node
     */
    public Solution(JigsawNode bNode, JigsawNode eNode) {
        super(bNode, eNode);
    }

    /**
     * Breadth-first search to solve n-puzzle problem.
     *
     * @param bNode begin node
     * @param eNode end node
     * @return true if solved
     */
    public boolean BFSearch(JigsawNode bNode, JigsawNode eNode) {
        Set<JigsawNode> visitedList = new HashSet<>(1000);
        Queue<JigsawNode> exploreList = new LinkedList<>();
        this.beginJNode = new JigsawNode(bNode);
        this.endJNode = new JigsawNode(eNode);
        this.currentJNode = null;
        int searchedNodesNum = 0;
        this.reset();
        visitedList.add(this.beginJNode);
        exploreList.add(this.beginJNode);

        while (!exploreList.isEmpty()) {
            searchedNodesNum++;
            this.currentJNode = exploreList.poll();
            if (this.currentJNode.equals(eNode)) {
                this.getPath();
                break;
            }
            for (int i = 0; i < 4; i++) {
                JigsawNode nextNode = new JigsawNode(this.currentJNode);
                if (nextNode.move(i) && !visitedList.contains(nextNode)) {
                    visitedList.add(nextNode);
                    exploreList.offer(nextNode);
                }
            }
        }
        System.out.println("Jigsaw BF Search Result:");
        System.out.println("Begin state:" + this.getBeginJNode().toString());
        System.out.println("End state:" + this.getEndJNode().toString());
        System.out.println("Solution Path: ");
        System.out.println(this.getSolutionPath());
        System.out.println("Total number of searched nodes:" + searchedNodesNum);
        System.out.println("Depth of the current node is:" + this.getCurrentJNode().getNodeDepth());
        System.out.println();
        return this.isCompleted();
    }

    /**
     * Estimate the priority value of node
     *
     * @param jNode the node to estimate
     */
    public void estimateValue(JigsawNode jNode) {
        int f0v = jNode.getParent().getEstimatedValue();
        int f1v = 0, f2v = 0, f3v = 0;
        int dimension = JigsawNode.getDimension();
        for (int i = 1; i <= dimension * dimension; i++) {
            if (i < dimension * dimension && jNode.getNodesState()[i] != 0 && jNode.getNodesState()[i] + 1 != jNode.getNodesState()[i + 1]) {
                f1v++;
            }
            if (jNode.getNodesState()[i] != 0 && jNode.getNodesState()[i] != i) {
                f2v++;
            }
            if (jNode.getNodesState()[i] != 0) {
                f3v += Math.abs((jNode.getNodesState()[i] + dimension - 1) / dimension - (i + dimension - 1) / dimension);
                f3v += Math.abs((jNode.getNodesState()[i] + dimension - 1) % dimension - (i + dimension - 1) % dimension);
            }
        }
        jNode.setEstimatedValue(f0v / F0W + f1v * F1W + f2v * F2W + f3v * F3W);
    }

    /**
     * The class about weight of estimate value, in order to find the weight automatically
     */
    public static class Weight {
        public int f0w; // weight of parent value
        public int f1w; // weight of invalid next position
        public int f2w; // weight of invalid position
        public int f3w; // weight of distance

        /**
         * Constructor
         *
         * @param w weight
         */
        public Weight(Weight w) {
            setWeight(w.f0w, w.f1w, w.f2w, w.f3w);
        }

        /**
         * Constructor
         *
         * @param f0w weight of parent value
         * @param f1w weight of invalid next position
         * @param f2w weight of invalid position
         * @param f3w weight of distance
         */
        public Weight(int f0w, int f1w, int f2w, int f3w) {
            setWeight(f0w, f1w, f2w, f3w);
        }

        /**
         * Set the value of weight
         *
         * @param f0w weight of parent value
         * @param f1w weight of invalid next position
         * @param f2w weight of invalid position
         * @param f3w weight of distance
         */
        public void setWeight(int f0w, int f1w, int f2w, int f3w) {
            int val;
            if (f1w == 0 || f2w == 0 || f3w == 0) {
                val = 1;
            } else {
                val = gcd(f1w, gcd(f2w, f3w));
            }
            this.f0w = f0w * val;
            this.f1w = f1w / val;
            this.f2w = f2w / val;
            this.f3w = f3w / val;
        }

        public boolean equals(Object weight) {
            if (!(weight instanceof Weight)) return false;
            Weight w = (Weight) weight;
            return f0w == w.f0w && f1w == w.f1w && f2w == w.f2w && f3w == w.f3w;
        }

        public int hashCode() {
            return ((f3w & 0xff) << 24) | ((f2w & 0xff) << 16) | ((f1w & 0xff) << 8) | (f0w & 0xff);
        }

        /**
         * Greatest common divisor of two numbers
         *
         * @param a the first number
         * @param b the second number
         * @return gcd of parameters
         */
        private int gcd(int a, int b) {
            return b == 0 ? a : gcd(b, a % b);
        }
    }
}
