package com.icytown.gridworld;

import static org.junit.Assert.*;

import com.icytown.gridworld.actor.Jumper;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;
import org.junit.Test;

import java.lang.reflect.Method;

public class JumperTest {
    @Test(timeout = 5000)
    public void canMove() {
        try {
            for (int i = 1; i <= 6; i++) {
                Method method = JumperTest.class.getDeclaredMethod("case" + i, new Class[]{String.class});
                method.invoke(this, "canMove");
            }
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    @Test(timeout = 5000)
    public void move() {
        try {
            for (int i = 1; i <= 3; i++) {
                Method method = JumperTest.class.getDeclaredMethod("case" + i, new Class[]{String.class});
                method.invoke(this, "move");
            }
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    @Test(timeout = 5000)
    public void act() {
        try {
            for (int i = 1; i <= 6; i++) {
                Method method = JumperTest.class.getDeclaredMethod("case" + i, new Class[]{String.class});
                method.invoke(this, "act");
            }
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    private void case1(String methodStr) {
        try {
            ActorWorld world = new ActorWorld();
            Jumper jumper = new Jumper();
            world.add(new Location(4, 4), jumper);
            Method method = jumper.getClass().getMethod(methodStr);
            Object obj = method.invoke(jumper);
            switch (methodStr) {
                case "canMove": {
                    assertEquals(obj, true);
                    break;
                }
                case "move":
                case "act": {
                    assertEquals(jumper.getLocation(), new Location(2, 4));
                    break;
                }
                default: {
                    fail("No support for this method.");
                }
            }
        } catch (Exception e) {
            fail("Oops, fail in case 1!");
        }
    }

    private void case2(String methodStr) {
        try {
            ActorWorld world = new ActorWorld();
            Jumper jumper = new Jumper();
            Rock rock = new Rock();
            world.add(new Location(4, 4), jumper);
            world.add(new Location(3, 4), rock);
            Method method = jumper.getClass().getMethod(methodStr);
            Object obj = method.invoke(jumper);
            switch (methodStr) {
                case "canMove": {
                    assertEquals(obj, true);
                    break;
                }
                case "move":
                case "act": {
                    assertEquals(jumper.getLocation(), new Location(2, 4));
                    break;
                }
                default: {
                    fail("No support for this method.");
                }
            }
        } catch (Exception e) {
            fail("Oops, fail in case 2!");
        }
    }

    private void case3(String methodStr) {
        try {
            ActorWorld world = new ActorWorld();
            Jumper jumper = new Jumper();
            Rock rock = new Rock();
            Flower flower = new Flower();
            world.add(new Location(4, 4), jumper);
            world.add(new Location(3, 4), rock);
            world.add(new Location(2, 4), flower);
            Method method = jumper.getClass().getMethod(methodStr);
            Object obj = method.invoke(jumper);
            switch (methodStr) {
                case "canMove": {
                    assertEquals(obj, true);
                    break;
                }
                case "move":
                case "act": {
                    assertEquals(jumper.getLocation(), new Location(2, 4));
                    assertEquals(flower.getGrid(), null);
                    break;
                }
                default: {
                    fail("No support for this method.");
                }
            }
        } catch (Exception e) {
            fail("Oops, fail in case 3!");
        }
    }

    private void case4(String methodStr) {
        try {
            ActorWorld world = new ActorWorld();
            Jumper jumper = new Jumper();
            Rock rock1 = new Rock();
            Rock rock2 = new Rock();
            world.add(new Location(4, 4), jumper);
            world.add(new Location(3, 4), rock1);
            world.add(new Location(2, 4), rock2);
            Method method = jumper.getClass().getMethod(methodStr);
            Object obj = method.invoke(jumper);
            switch (methodStr) {
                case "canMove": {
                    assertEquals(obj, false);
                    break;
                }
                case "act": {
                    assertEquals(jumper.getLocation(), new Location(4, 4));
                    assertEquals(jumper.getDirection(), Location.NORTHEAST);
                    break;
                }
                default: {
                    fail("No support for this method.");
                }
            }
        } catch (Exception e) {
            fail("Oops, fail in case 4!");
        }
    }

    private void case5(String methodStr) {
        try {
            ActorWorld world = new ActorWorld();
            Jumper jumper1 = new Jumper();
            Jumper jumper2 = new Jumper();
            world.add(new Location(4, 4), jumper1);
            world.add(new Location(2, 4), jumper2);
            Method method = jumper1.getClass().getMethod(methodStr);
            Object obj1 = method.invoke(jumper1);
            Object obj2 = method.invoke(jumper2);
            switch (methodStr) {
                case "canMove": {
                    assertEquals(obj1, false);
                    assertEquals(obj2, true);
                    break;
                }
                case "act": {
                    assertEquals(jumper1.getLocation(), new Location(4, 4));
                    assertEquals(jumper1.getDirection(), Location.NORTHEAST);
                    assertEquals(jumper2.getLocation(), new Location(0, 4));
                    break;
                }
                default: {
                    fail("No support for this method.");
                }
            }
        } catch (Exception e) {
            fail("Oops, fail in case 5!");
        }
    }

    private void case6(String methodStr) {
        try {
            ActorWorld world = new ActorWorld();
            Jumper jumper1 = new Jumper();
            Jumper jumper2 = new Jumper();
            world.add(new Location(4, 4), jumper1);
            world.add(new Location(2, 4), jumper2);
            jumper2.setDirection(Location.SOUTH);
            Method method = jumper1.getClass().getMethod(methodStr);
            Object obj1 = method.invoke(jumper1);
            Object obj2 = method.invoke(jumper2);
            switch (methodStr) {
                case "canMove": {
                    assertEquals(obj1, false);
                    assertEquals(obj2, false);
                    break;
                }
                case "act": {
                    assertEquals(jumper1.getLocation(), new Location(4, 4));
                    assertEquals(jumper1.getDirection(), Location.NORTHEAST);
                    assertEquals(jumper2.getLocation(), new Location(2, 4));
                    assertEquals(jumper2.getDirection(), Location.SOUTHWEST);
                    break;
                }
                default: {
                    fail("No support for this method.");
                }
            }
        } catch (Exception e) {
            fail("Oops, fail in case 6!");
        }
    }
}
