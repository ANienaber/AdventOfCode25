package adventOfCode;

import java.util.ArrayList;
import java.util.List;

public class Day8 extends Day{

    public Day8(int day) {
        super(day,
                40,
                25272,
                false,
                2
        );
    }

    protected long part1(List<String> lines) {
        long result = 0;

        List<JunctionBox> boxes = new ArrayList<>();
        for (String line: lines) {
            String[] coordinates = line.split(",");
            boxes.add(new JunctionBox(Long.parseLong(coordinates[0]), Long.parseLong(coordinates[1]), Long.parseLong(coordinates[2])));
        }
        print(boxes.toString());

        List<Connection> connections = new ArrayList<>();
        int numberOfConnections = this.isTest() ? 10 : 1000;
        for (int i=0; i < boxes.size(); i++) {
            for (int j=0; j < boxes.size(); j++) {
                if (i==j) {
                    continue;
                }

                JunctionBox box1 = boxes.get(i);
                JunctionBox box2 = boxes.get(j);
                double distance = Math.sqrt(Math.pow(box1.x - box2.x, 2) + Math.pow(box1.y - box2.y, 2) + Math.pow(box1.z - box2.z, 2));
                if (connections.size() < numberOfConnections) {
                    insertByDistance(connections, new Connection(box1, box2, distance));
                } else if (connections.getLast().distance > distance) {
                    insertByDistance(connections, new Connection(box1, box2, distance));
                    if (connections.size() > numberOfConnections) {
                        connections.removeLast();
                    }
                }
            }
        }
        print(connections.toString());

        List<List<JunctionBox>> circuits = new ArrayList<>();
        for (Connection con : connections) {
            int circuitOfBox1 = -1;
            int circuitOfBox2 = -1;
            for (int i=0;i<circuits.size();i++) {
                if (circuits.get(i).contains(con.box1)) {
                    circuitOfBox1 = i;
                }
                if (circuits.get(i).contains(con.box2)) {
                    circuitOfBox2 = i;
                }
            }

            if (circuitOfBox1 == -1 && circuitOfBox2 == -1) {
                List<JunctionBox> newCircuit = new ArrayList<>();
                newCircuit.add(con.box1);
                newCircuit.add(con.box2);
                circuits.add(newCircuit);
            } else if (circuitOfBox1 != -1 && circuitOfBox2 != -1 && circuitOfBox1 != circuitOfBox2){
                circuits.get(circuitOfBox1).addAll(circuits.get(circuitOfBox2));
                circuits.remove(circuitOfBox2);
            } else if (circuitOfBox1 != -1 && circuitOfBox2 == -1) {
                circuits.get(circuitOfBox1).add(con.box2);
            } else if (circuitOfBox1 == -1) {
                circuits.get(circuitOfBox2).add(con.box1);
            }
        }

        circuits.sort((list1, list2) -> list2.size() - list1.size());
        for (List<JunctionBox> circuit: circuits) {
            print(circuit.toString());
        }

        return result = (long) circuits.get(0).size() * circuits.get(1).size() * circuits.get(2).size();
    }

    private void insertByDistance(List<Connection> connections, Connection connection) {
        if (connections.contains(new Connection(connection.box2, connection.box1, connection.distance))) {
            return;
        }
        boolean success = false;
        for (int c=0; c < connections.size(); c++) {
            if (connections.get(c).distance > connection.distance) {
                connections.add(c, connection);
                success = true;
                break;
            }
        }
        if (!success) {
            connections.add(connection);
        }
    }

    protected long part2(List<String> lines) {
        long result = 0;

        List<JunctionBox> boxes = new ArrayList<>();
        for (String line: lines) {
            String[] coordinates = line.split(",");
            boxes.add(new JunctionBox(Long.parseLong(coordinates[0]), Long.parseLong(coordinates[1]), Long.parseLong(coordinates[2])));
        }
        print(boxes.toString());

        List<Connection> connections = new ArrayList<>();
        int numberOfConnections = 10000;
        for (int i=0; i < boxes.size(); i++) {
            for (int j=0; j < boxes.size(); j++) {
                if (i==j) {
                    continue;
                }

                JunctionBox box1 = boxes.get(i);
                JunctionBox box2 = boxes.get(j);
                double distance = Math.sqrt(Math.pow(box1.x - box2.x, 2) + Math.pow(box1.y - box2.y, 2) + Math.pow(box1.z - box2.z, 2));
                if (connections.size() < numberOfConnections) {
                    insertByDistance(connections, new Connection(box1, box2, distance));
                } else if (connections.getLast().distance > distance) {
                    insertByDistance(connections, new Connection(box1, box2, distance));
                    if (connections.size() > numberOfConnections) {
                        connections.removeLast();
                    }
                }
            }
        }
        print(connections.toString());

        List<List<JunctionBox>> circuits = new ArrayList<>();
        for (Connection con : connections) {
            int circuitOfBox1 = -1;
            int circuitOfBox2 = -1;
            for (int i=0;i<circuits.size();i++) {
                if (circuits.get(i).contains(con.box1)) {
                    circuitOfBox1 = i;
                }
                if (circuits.get(i).contains(con.box2)) {
                    circuitOfBox2 = i;
                }
            }

            if (circuitOfBox1 == -1 && circuitOfBox2 == -1) {
                List<JunctionBox> newCircuit = new ArrayList<>();
                newCircuit.add(con.box1);
                newCircuit.add(con.box2);
                circuits.add(newCircuit);
            } else if (circuitOfBox1 != -1 && circuitOfBox2 != -1 && circuitOfBox1 != circuitOfBox2){
                circuits.get(circuitOfBox1).addAll(circuits.get(circuitOfBox2));
                circuits.remove(circuitOfBox2);
            } else if (circuitOfBox1 != -1 && circuitOfBox2 == -1) {
                circuits.get(circuitOfBox1).add(con.box2);
            } else if (circuitOfBox1 == -1) {
                circuits.get(circuitOfBox2).add(con.box1);
            }
            if (circuits.getFirst().size() == boxes.size()) {
                result = con.box1.x * con.box2.x;
                break;
            }
        }
        if (result == 0) {
            circuits.sort((list1, list2) -> list2.size() - list1.size());
            System.out.println("Not enough connections! Biggest circuit is " + circuits.getFirst().size() + " out of " + boxes.size());
        }
        return result;
    }

    private record JunctionBox(long x, long y, long z) {};

    private record Connection(JunctionBox box1, JunctionBox box2, double distance) {};
}
