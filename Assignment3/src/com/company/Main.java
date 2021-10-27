package com.company;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class RollDice{
    Random R = new Random();
    int roll_value;
    public int roll(){
        roll_value = 1 + R.nextInt(2);
        return roll_value;
    }
}

class player {
    private String player_name;
    private int player_points, player_position;

    public player() {
        this.player_name = null;
        this.player_points = 0;
        this.player_position = -10;
    }
    public int getPlayer_points() {
        return player_points;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_points(int player_points) {
        this.player_points = player_points;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public void setPlayer_position(int player_position) {
        this.player_position = player_position;
    }

    public int getPlayer_position() {
        return player_position;
    }
}
class floor {
    private int floor_points, floor_position, floor_position_move;
    private String floor_name;

    public floor() {
        this.floor_points = 0;
        this.floor_name = null;
        this.floor_position_move = 0;
    }
    public void setFloor_points(int points) {
        this.floor_points = points;
    }

    public int getFloor_points() {
        return floor_points;
    }

    public int getFloor_position() {
        return floor_position;
    }

    public void setFloor_position(int fposition) {
        this.floor_position = fposition;
    }

    public void setFloor_position_move(int floor_position_move) {
        this.floor_position_move = floor_position_move;
    }

    public int getFloor_position_move() {
        return floor_position_move;
    }

    public void setFloor_name(String fname) {
        this.floor_name = fname;
    }

    public String getFloor_name() {
        return floor_name;
    }
}

class empty_floor extends floor {
    public empty_floor(int fposition) {
        this.setFloor_name("empty ladder");
        this.setFloor_position(fposition);
        this.setFloor_points(1);
        this.setFloor_position_move(0);
    }
}

class ladder_floor extends floor {
    public ladder_floor(int fposition) {
        this.setFloor_name("ladder ladder");
        this.setFloor_position(fposition);
        this.setFloor_points(2);
        this.setFloor_position_move(4);
    }
}

class elevator_floor extends floor {
    public elevator_floor(int fposition) {
        this.setFloor_name("elevator ladder");
        this.setFloor_position(fposition);
        this.setFloor_points(4);
        this.setFloor_position_move(8);
    }
}

class snake_floor extends floor {
    public snake_floor(int fposition) {
        this.setFloor_name("snake ladder");
        this.setFloor_position(fposition);
        this.setFloor_points(-2);
        this.setFloor_position_move(-4);
    }
}

class kingcobra_floor extends floor {
    public kingcobra_floor(int fposition) {
        this.setFloor_name("king cobra ladder");
        this.setFloor_position(fposition);
        this.setFloor_points(-4);
        this.setFloor_position_move(-8);
    }
}

public class Main {

    public static void main(String[] args) {
        ArrayList<floor> floor_objects = new ArrayList<>();
        empty_floor emp1 = new empty_floor(0);
        floor_objects.add(emp1);

        empty_floor emp2 = new empty_floor(1);
        floor_objects.add(emp2);

        elevator_floor ele = new elevator_floor(2);
        floor_objects.add(ele);

        empty_floor emp3 = new empty_floor(3);
        floor_objects.add(emp3);

        empty_floor emp4 = new empty_floor(4);
        floor_objects.add(emp4);

        snake_floor s5 = new snake_floor(5);
        floor_objects.add(s5);

        empty_floor emp5 = new empty_floor(6);
        floor_objects.add(emp5);

        empty_floor emp6 = new empty_floor(7);
        floor_objects.add(emp6);

        ladder_floor lad = new ladder_floor(8);
        floor_objects.add(lad);

        empty_floor emp7 = new empty_floor(9);
        floor_objects.add(emp7);

        empty_floor emp8 = new empty_floor(10);
        floor_objects.add(emp8);

        kingcobra_floor king = new kingcobra_floor(11);
        floor_objects.add(king);

        empty_floor emp9 = new empty_floor(12);
        floor_objects.add(emp9);

        empty_floor emp10 = new empty_floor(13);
        floor_objects.add(emp10);

        System.out.println("Enter the player name and hit enter");
        Scanner sc = new Scanner(System.in);
        String Name = sc.nextLine();
        player player1 = new player();
        RollDice rd = new RollDice();
        player1.setPlayer_name(Name);
        System.out.println("The game setup is ready");
        boolean game_over = false;
        while (!game_over) {
            if (player1.getPlayer_position() == 13) {
                System.out.println("Game Over\n" + player1.getPlayer_name() + " accumulated " + player1.getPlayer_points() + " points");
                game_over = true;
                break;
            }
            else {
                if (player1.getPlayer_position() == -10) {
                    System.out.print("Hit enter to roll the dice");
                    sc.nextLine();
                    rd.roll();
                    System.out.println("Dice gave "+ rd.roll_value);
                    while (rd.roll_value != 1){
                        System.out.println("Game cannot start until you get 1");
                        System.out.print("Hit enter to roll the dice");
                        sc.nextLine();
                        rd.roll();
                        System.out.println("Dice gave "+ rd.roll_value);
                    }
                    player1.setPlayer_position(-1);
                }
                else {
                    System.out.print("Hit enter to roll the dice");
                    sc.nextLine();
                    rd.roll();
                    System.out.println("Dice gave "+ rd.roll_value);
                }
            }
            if (player1.getPlayer_position() + rd.roll_value > 13) {
                System.out.println("player cannot move");
            }
            else {
                int player_position = player1.getPlayer_position() + rd.roll_value;
                player1.setPlayer_position(player_position);
                System.out.println("Player position Floor-" + player1.getPlayer_position());
                System.out.println(player1.getPlayer_name() + " has reached " + floor_objects.get(player1.getPlayer_position()).getFloor_name());
                player1.setPlayer_points(player1.getPlayer_points() + floor_objects.get(player1.getPlayer_position()).getFloor_points());
                System.out.println("Total points -> " + player1.getPlayer_points());
                if (floor_objects.get(player1.getPlayer_position()).getFloor_points() != 1) {
                    player1.setPlayer_position(player1.getPlayer_position() + floor_objects.get(player1.getPlayer_position()).getFloor_position_move());
                    System.out.println("Player position Floor-" + player1.getPlayer_position());
                    System.out.println(player1.getPlayer_name() + " has reached " + floor_objects.get(player1.getPlayer_position()).getFloor_name());
                    player1.setPlayer_points(player1.getPlayer_points() + floor_objects.get(player1.getPlayer_position()).getFloor_points());
                    System.out.println("Total points -> " + player1.getPlayer_points());
                }
            }
            if (player1.getPlayer_points() == 13)
                game_over = true;
        }
    }
}