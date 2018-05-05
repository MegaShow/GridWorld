package solution;

import jigsaw.Jigsaw;
import jigsaw.JigsawNode;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

public final class AutoRunValue {
    private final static int ASTAR_UPPER_LIMIT = 29000;
    private final static int ASTAR_TEST_TIME = 3;
    private final static int ASTAR_RANDOM_TIME = 500;
    private final static JigsawNode DEST_NODE = new JigsawNode(new int[]{25, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 0});
    private final static String runtimeToken = "Runtime";

    private AutoRunValue() {
        // Do nothing
    }

    public static void main(String[] main) throws FileNotFoundException, UnsupportedEncodingException {
        InputTest();
    }

    private static void InputTest() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("out.txt", "UTF-8");
        Scanner scan = new Scanner(System.in);
        Solution solution = new Solution();
        int input;
        writer.println("f0w,f1w,f2w,f3w,success");
        for (int f1w = 1; f1w <= 10; f1w++) {
            for (int f3w = 1; f3w <= 10; f3w++) {
                int f2w = 0;
                input = 20;
                solution.setWeight(new Solution.Weight(input, f1w, f2w, f3w));
                int result = 0;
                for (int i = 0; i < ASTAR_RANDOM_TIME; i++) {
                    // if (i % 10 == 0) System.out.println(i);
                    int searchedNodesNum = TestAStar(solution, Jigsaw.scatter(DEST_NODE, 1000), DEST_NODE);
                    if (searchedNodesNum >= ASTAR_UPPER_LIMIT) {
                        result++;
                    }
                }
                String str = input + "," + f1w + "," + f2w + "," + f3w + "," + (ASTAR_RANDOM_TIME - result);
                System.out.println(str);
                writer.println(str);
            }
        }
        /*while ((input = scan.nextInt()) != 0) {
            int f1w = scan.nextInt();
            int f2w = scan.nextInt();
            int f3w = scan.nextInt();
            solution.setWeight(new Solution.Weight(input, f1w, f2w, f3w));
            int result = 0;
            for (int i = 0; i < ASTAR_RANDOM_TIME; i++) {
                // if (i % 10 == 0) System.out.println(i);
                int searchedNodesNum = TestAStar(solution, Jigsaw.scatter(DEST_NODE, 1000), DEST_NODE);
                if (searchedNodesNum >= ASTAR_UPPER_LIMIT) {
                    result++;
                }
            }
            String str = input + "," + f1w + "," + f2w + "," + f3w + "," + (ASTAR_RANDOM_TIME - result);
            System.out.println(str);
            writer.println(str);
        }*/
        writer.close();
    }

    private static void StandardTest() throws FileNotFoundException, UnsupportedEncodingException {
        JigsawNode[] nodes = new JigsawNode[ASTAR_TEST_TIME];
        int[][] arr = {
                {16, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 18, 12, 14, 15, 0, 16, 13, 19, 20, 21, 17, 22, 23, 24},
                {1, 0, 1, 8, 23, 24, 16, 4, 9, 7, 20, 12, 3, 10, 15, 17, 6, 22, 5, 19, 14, 21, 2, 18, 11, 13},
                {24, 8, 2, 13, 9, 3, 15, 22, 5, 6, 21, 12, 4, 14, 11, 1, 18, 20, 19, 7, 17, 23, 24, 16, 0, 10}
        };
        for (int i = 0; i < ASTAR_TEST_TIME; i++) {
            nodes[i] = new JigsawNode(arr[i]);
        }
        PrintWriter writer = new PrintWriter("out.txt", "UTF-8");
        Solution solution = new Solution();
        Set<Solution.Weight> set = new HashSet<>();
        for (int f0w = 0; f0w <= 0; f0w++) {
            for (int f1w = 1; f1w <= 1; f1w++) {
                for (int f2w = 0; f2w <= 0; f2w++) {
                    for (int f3w = 1; f3w <= 1; f3w++) {
                        set.add(new Solution.Weight(f0w, f1w, f2w, f3w));
                    }
                }
            }
        }
        writer.print("f0w");
        for (Solution.Weight w : set) writer.print("," + w.f0w);
        writer.print("\nf1w");
        for (Solution.Weight w : set) writer.print("," + w.f1w);
        writer.print("\nf2w");
        for (Solution.Weight w : set) writer.print("," + w.f2w);
        writer.print("\nf3w");
        for (Solution.Weight w : set) writer.print("," + w.f3w);
        writer.println("");
        System.out.println("Generate weight set " + set.size() + " size.");
        for (int i = 0; i < ASTAR_TEST_TIME; i++) {
            writer.print("\"" + nodes[i] + "\"");
            for (Solution.Weight  w : set) {
                solution.setWeight(w);
                int searchedNodesNum = TestAStar(solution, nodes[i], DEST_NODE);
                writer.print("," + searchedNodesNum);
            }
            writer.println("");
        }
        writer.close();
    }

    private static void RandomTest() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("out.txt", "UTF-8");
        Solution solution = new Solution();
        Set<Solution.Weight> wSet = new HashSet<>();
        Set<JigsawNode> nSet = new HashSet<>();
        Map<Solution.Weight, Integer> successNum = new HashMap<>();
        Map<Solution.Weight, Map<JigsawNode, Integer>> steps = new HashMap<>();
        for (int i = 0; i < ASTAR_RANDOM_TIME; i++) {
            nSet.add(Jigsaw.scatter(DEST_NODE, 1000));
        }
        for (int f0w = 100; f0w <= 100; f0w++) {
            for (int f1w = 5; f1w <= 9; f1w++) {
                for (int f2w = 4; f2w <= 6; f2w++) {
                    for (int f3w = 4; f3w <= 6; f3w++) {
                        Solution.Weight w = new Solution.Weight(f0w, f1w, f2w, f3w);
                        wSet.add(w);
                        successNum.put(w, 0);
                    }
                }
            }
        }
        Set<Solution.Weight> temp = new HashSet<>(wSet);
        int i = 0;
        for (Solution.Weight w : temp) {
            System.out.println(i++);
            solution.setWeight(w);
            Map<JigsawNode, Integer> subSteps = new HashMap<>();
            steps.put(w, subSteps);
            for (JigsawNode n : nSet) {
                int searchedNodesNum = TestAStar(solution, n, DEST_NODE);
                if (searchedNodesNum == ASTAR_UPPER_LIMIT) {
                    successNum.put(w, successNum.get(w) + 1);
                    if (successNum.get(w) >= ASTAR_RANDOM_TIME / 10) {
                        wSet.remove(w);
                        successNum.remove(w);
                        steps.remove(w);
                        break;
                    }
                }
                subSteps.put(n, searchedNodesNum);
            }
        }
        writer.println("f0w,f1w,f2w,f3w");
        for (Solution.Weight w: wSet) {
            writer.println(w.f0w + "," + w.f1w + "," + w.f2w + "," + w.f3w);
        }
        System.out.println("Generate weight set " + wSet.size() + " size.");
        writer.close();
    }

    private static int TestAStar(Jigsaw solution, JigsawNode startNode, JigsawNode destNode) {
        try {
            if (!solution.ASearch(startNode, destNode)) {
                return ASTAR_UPPER_LIMIT;
            }
        } catch (Throwable th) {
            th.printStackTrace();
            return ASTAR_UPPER_LIMIT;
        }
        List<JigsawNode> solutionPath = solution.getPath();
        if (!Jigsaw.isValidPath(solutionPath, startNode, destNode)) {
            return ASTAR_UPPER_LIMIT;
        }
        return solution.getSearchedNodesNum();
    }
}
