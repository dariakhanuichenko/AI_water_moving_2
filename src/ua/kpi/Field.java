package ua.kpi;


import java.util.ArrayList;
import java.util.List;

public class Field implements Cloneable {

    private List<Cell> cells;

    public List<Cell> getCells() {
        return cells;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }

    public Field() {
        cells = new ArrayList<>(8);
        cells.add(new Cell(new Ball(Color.BLACK, false, Actions.NOTHING)));
        cells.add(new Cell(new Ball(Color.BLACK, false, Actions.NOTHING)));
        cells.add(new Cell(new Ball(Color.BLACK, false, Actions.NOTHING)));
        cells.add(new Cell(new Ball(Color.BLACK, false, Actions.NOTHING)));
        cells.add(new Cell(null));
        cells.add(new Cell(new Ball(Color.WHITE, false, Actions.NOTHING)));
        cells.add(new Cell(new Ball(Color.WHITE, false, Actions.NOTHING)));
        cells.add(new Cell(new Ball(Color.WHITE, false, Actions.NOTHING)));
    }

    @Override
    protected Field clone() {
        Field clone = null;
        try {
            clone = (Field) super.clone();
            clone.setCells(new ArrayList<>(8));
            for (Cell cell : cells) {
                clone.getCells().add(cell.clone());
            }
        } catch (CloneNotSupportedException e) {
            System.out.println(e);
        }
        return clone;
    }

    public List<Cell> swapBalls(List<Cell> cells, Integer i, Integer j) {

        Cell tmp = cells.get(i);
        cells.set(i, cells.get(j));
        cells.set(j, tmp);
        return cells;
    }

    private boolean deadEnd(List<Cell> cells, Integer i, Integer counter) {

//        System.out.println("------deadend--------");
//        cells.forEach(System.out::println);
        for (int j = 0; j < cells.size(); j++) {
            if (j == i) continue;
            else if (cells.get(j).getBall().getAction() != Actions.NOTHING) return false;
        }
        return true;


    }

    public Integer getIndexOfEmptyCell(List<Cell> cells) {
        for (int i = 0; i < cells.size(); i++) {
            if (cells.get(i).isEmpty()) return i;
        }
        ;
        return -1;
    }

    public void algorithm(Field field) {
        Utility.canMove(field);
        for (int i = 0; i < field.getCells().size(); i++) {
            System.out.println("///////////////////////////////");
            if (field.getCells().get(i).getBall() != null && field.getCells().get(i).getBall().getAction() != Actions.NOTHING) {
                System.out.println(field.getCells().get(i).getBall().getAction());
//                field.getCells().forEach(System.out::println);
//                System.out.println("//////////////////////////////");
                Field clone = field.clone();
                clone.getCells().forEach(System.out::println);
                swapBalls(clone.getCells(), i, clone.getCells().get(i).getBall().getAction().returnTargetCell(i));
                System.out.println("---------------------------------");
                clone.getCells().forEach(System.out::println);
                if (success(clone)){
                    System.out.println("СУКСССССУУУУУУУУСССС");
                }
                algorithm(clone);
            }
        }
        System.out.println("///////////////////////////////");

    }

    boolean success(Field field) {
        if (field.getCells().get(0).getBall() != null && field.getCells().get(0).getBall().getColor().equals(Color.WHITE) &&
                field.getCells().get(1).getBall() != null && field.getCells().get(1).getBall().getColor().equals(Color.WHITE) &&
                field.getCells().get(2).getBall() != null && field.getCells().get(2).getBall().getColor().equals(Color.WHITE) &&
                field.getCells().get(3).getBall() == null &&
                field.getCells().get(4).getBall() != null && field.getCells().get(4).getBall().getColor().equals(Color.BLACK) &&
                field.getCells().get(5).getBall() != null && field.getCells().get(5).getBall().getColor().equals(Color.BLACK) &&
                field.getCells().get(6).getBall() != null && field.getCells().get(6).getBall().getColor().equals(Color.BLACK) &&
                field.getCells().get(7).getBall() != null && field.getCells().get(7).getBall().getColor().equals(Color.BLACK)
        ){
            return true;
        }
        return false;
    }
}

    /*
    method(field){
        for(cell){
            if (cell.getball.canmove){
                move;
                cloneField = clone field;
                method(cloneField);
            }
        }
    }*/