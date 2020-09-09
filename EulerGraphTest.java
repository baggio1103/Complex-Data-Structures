package EulerTree;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class EulerGraphTest {

    @Test
    public void convertIntoGraph() {
        Node node = new Node(1);
        Node node3 = new Node(3);
        Node node5 = new Node(5);
        Node node7 = new Node(7);
        Node node9 = new Node(9);
        node.childNodes.add(node3);
        node.childNodes.add(node5);
        node.childNodes.add(node7);
        node5.childNodes.add(node9);

        EulerGraph graph = new EulerGraph(node);
        assertNull(node.parent);
        assertNull(node3.parent);
        assertNull(node5.parent);
        assertNull(node7.parent);
        assertNull(node9.parent);
        graph.convertIntoGraph(node);
        assertEquals(node3.parent, node);
        assertEquals(node5.parent, node);
        assertEquals(node7.parent, node);
        assertEquals(node9.parent, node5);
        assertEquals(node9.parent.parent, node);
    }

    @Test
    public void addEdge() {
        Node node = new Node(1);        //    3
        Node node3 = new Node(3);       //      \
        Node node5 = new Node(5);       //        1
        node.childNodes.add(node3);           //       /
        node.childNodes.add(node5);           //    5


        Node node7 = new Node(7);       //          9
        Node node9 = new Node(9);       //        /
        Node node11 = new Node(11);     //      7
        node7.childNodes.add(node9);          //        \
        node7.childNodes.add(node11);         //          11

        EulerGraph graph = new EulerGraph(node);
        //The Euler Route of the first graph: 1 - 3 - 1 - 5 - 1
        EulerGraph graph2 = new EulerGraph(node7);
        //The Euler Route of the second graph: 7 - 9 - 7 - 11 - 7
        assertFalse(graph.doTheyBelongToOneGraph(node, node7));
        //adding an edge
        graph.addEdge(graph, node, graph2, node7);
        assertTrue(graph.doTheyBelongToOneGraph(node, node7));
        List<Node> route = graph.eulerRoute;
        graph.printRoute(route);
        //Final Euler Route: 1 - 3 - 1 - 5 - 1 - 7 - 9 - 7 - 11 - 7 - 1
    }

    @Test
    public void removeEdge() {
        Node node = new Node(1);                    //   1                              9
        Node node3 = new Node(3);                   //     \                         /
        Node node5 = new Node(5);                   //      \                      /
        Node node7 = new Node(7);                   //         5 --------------  7
        Node node9 = new Node(9);                   //      /                       \
        Node node11 = new Node(11);                 //     /                          \
        node7.childNodes.add(node9);                      //    3                             11
        node7.childNodes.add(node11);
        node5.childNodes.add(node3);                      //                                   1                            9
        node5.childNodes.add(node7);                      //                                     \                        /
        node.childNodes.add(node5);                       //                                      \                      /
        EulerGraph graph = new EulerGraph(node);          //      After removing the edge  =>       5                   7
        List<Node> route = graph.getEulerRoute();         //                                      /                      \
        graph.printRoute(route);                          //                                     /                         \
        //Initial Euler Route: 1 - 5 - 3 - 5 - 7 - 9 - 7 - 11 - 7 - 5 - 1                      3                            11
        graph.removeEdge(node7, node5);
        route = graph.getEulerRoute();
        graph.printRoute(route);
        //Final Euler Route: Graph1 = {5 - 1 - 5 - 3 - 5}, Graph2 = {7 - 9 - 7 - 11 - 7}
        //Finally, we have the list that contains Euler Routes of {Graph1 + Graph2}.
        assertEquals(5, route.get(0).value);
        assertEquals(7, route.get(route.size()-1).value);
    }

    @Test
    public void setRoot() {
        Node node = new Node(1);                    //   1                              9
        Node node3 = new Node(3);                   //     \                         /
        Node node5 = new Node(5);                   //      \                      /
        Node node7 = new Node(7);                   //         5 --------------  7
        Node node9 = new Node(9);                   //      /                       \
        Node node11 = new Node(11);                 //     /                          \
        node7.childNodes.add(node9);                      //    3                             11
        node7.childNodes.add(node11);
        node5.childNodes.add(node3);
        node5.childNodes.add(node7);
        node.childNodes.add(node5);
        EulerGraph graph = new EulerGraph(node);
        List<Node> route = graph.constructEulerRoute(graph.root);
        graph.printRoute(route);
        //Initial Euler Route: 1 - 5 - 3 - 5 - 7 - 9 - 7 - 11 - 7 - 5 - 1
        graph.setRoot(node7);
        route = graph.eulerRoute;
        graph.printRoute(route);
        //Final Euler Route: 7 - 9 - 7 - 11 - 7 - 5 - 1 - 5 -3 - 5 - 7
        assertEquals(7, route.get(0).value);
        assertEquals(9, route.get(1).value);
        assertEquals(7, route.get(2).value);
        assertEquals(1, route.get(6).value);

    }

    @Test
    public void doTheyBelongToOneGraph() {
        Node node = new Node(1);                    //   1                              9
        Node node3 = new Node(3);                   //     \                         /
        Node node5 = new Node(5);                   //      \                      /
        Node node7 = new Node(7);                   //         5 --------------  7
        Node node9 = new Node(9);                   //      /                       \
        Node node11 = new Node(11);                 //     /                          \
        node7.childNodes.add(node9);                      //    3                             11
        node7.childNodes.add(node11);
        node5.childNodes.add(node3);                      //                13
        node5.childNodes.add(node7);
        node.childNodes.add(node5);
        Node node13 = new Node(13);
        EulerGraph graph = new EulerGraph(node);
        assertTrue(graph.doTheyBelongToOneGraph(node3, node5));
        assertFalse(graph.doTheyBelongToOneGraph(node3, node13));
    }

}