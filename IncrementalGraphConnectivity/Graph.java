package IncrementalConnectivity;

import java.util.ArrayList;
import java.util.List;

public class Graph {

     public DisjointSetUnion doubleEdgeConnectivityComponent;
     public DisjointSetUnion connectivityComponent;
     public List<Bridge> bridges;

     public Graph() {
          doubleEdgeConnectivityComponent = new DisjointSetUnion();
          connectivityComponent = new DisjointSetUnion();
          bridges = new ArrayList<>();
     }

     public void addNode(Node node){
          doubleEdgeConnectivityComponent.makeSet(node);
          connectivityComponent.makeSet(node);
     }

     public void addEdge(Node from, Node to){
          if (doubleEdgeConnectivityComponent.areNodesConnected(from, to)){

          }else if (!connectivityComponent.areNodesConnected(from, to)){
               connectivityComponent.unite(from, to);
               addBridge(from, to);
          }else{
          // nodes belong to one component connectivity but different DoubleEdgeConnectivity
               List<Node> doubleConnectCom = checkEdge(from, to);
               for (int i = 0; i < doubleConnectCom.size()-1; i++){
                    doubleEdgeConnectivityComponent.unite(from, to);
               }
          }
     }

     public void addBridge(Node from, Node to){
          Node rootNode = connectivityComponent.find(from);
          bridges.add(new Bridge(rootNode, to));
     }

     public List<Node> checkEdge(Node fromNode, Node toNode){
          Node tempFrom = fromNode;
          Node tempTo = toNode;
          List<Node> edges = new ArrayList<>();
          List<Bridge> oddBridges;

          while (tempFrom != null && tempFrom != tempTo){
               edges.add(tempFrom);
               tempFrom.hit = true;
               tempFrom = tempFrom.parent;
          }

          if (tempFrom == tempTo){
               oddBridges = new ArrayList<>();
               for (int i = 0; i < edges.size()-1; i++){
                    oddBridges.add(new Bridge(edges.get(i),edges.get(i+1)));
               }
          }else {
               while (!tempTo.hit){
                    tempTo.hit = true;
                    edges.add(tempTo);
                    tempTo = tempTo.parent;
               }

               int from = edges.indexOf(tempTo);
               int to = edges.indexOf(toNode);

               for (int i = from +1 ; i < to; i++){
                    edges.remove(i);
               }

               oddBridges = findOddBridges(from, to, edges);
          }
          removeBridges(oddBridges);
          return edges;

     }

     public void deleteOddEdges(int from, int to, List<Node> list){
          for (int i = from + 1 ; i < to; i++){
               list.remove(i);
          }
     }

     public void removeBridges(List<Bridge> oddBridges){
          bridges.removeAll(oddBridges);
     }

     public List<Bridge> findOddBridges(int from, int to, List<Node> list){
          List<Bridge> oddBridges = new ArrayList<>();
          for (int i = 0; i < from-1; i++){
              oddBridges.add(new Bridge( list.get(i), list.get(i+1)));
          }

          for (int i = to; i < list.size()-1; i++){
               oddBridges.add(new Bridge(list.get(i), list.get(i+1)));
          }
          oddBridges.add(new Bridge(list.get(list.size()-1), list.get(from)));
          return oddBridges;
     }

     public List<Bridge> getBridges(){
          return bridges;
     }

}

class Bridge{
     public Node from;
     public Node to;

     public Bridge(Node from, Node to) {
          this.from = from;
          this.to = to;
     }

     @Override
     public String toString() {
          return "Bridge{" +
                  "from=" + from.value +
                  ", to=" + to.value +
                  '}';
     }

}
