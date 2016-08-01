package manager;

import com.huantt.pacmangame.gui.GUI;
import com.huantt.pacmangame.model.*;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * Created by Huan on 7/22/2016.
 */
public class GameManager {
    private Pacman pacman;
    private ArrayList<Item> iteams;
    private ArrayList<Bullet> bullets;
    private ArrayList<Ghost> ghosts;
    private Swirl swirl;
    private int pacmanNextOrient;
    private int countDowntFirePacman;
    private int score;
    public static final int NUM_OF_ROWS_MAP = 22;
    public static final int NUM_OF_COLUMNS_MAP = 21;
    private static final int MAX_COUNTDOWN_PACMAN = 100;
    private int numberOfBeanNomal;

    public GameManager() {
        initializeGhost();
        swirl = new Swirl((NUM_OF_COLUMNS_MAP / 2) * Item.SIZE, (NUM_OF_ROWS_MAP / 2 - 1) * Item.SIZE);
        pacmanNextOrient = Pacman.LEFT;
        pacman = new Pacman((NUM_OF_COLUMNS_MAP / 2) * Item.SIZE, (NUM_OF_ROWS_MAP - 2) * Item.SIZE, Pacman.UP, 1);
        iteams = new ArrayList<>();
        bullets = new ArrayList<>();
        loadMap("map.txt");
    }

    public int getScore() {
        return score;
    }

    public int getPacmanNextOrient() {
        return pacmanNextOrient;
    }

    public void setPacmanNextOrient(int pacmanNextOrient) {
        this.pacmanNextOrient = pacmanNextOrient;
    }

    public void drawPacMan(Graphics2D graphics2D) {
        pacman.draw(graphics2D);
    }

    public void movePacman(int count) {
        pacman.move(count);
    }

    public void chanePacmanOrient(int orient) {
        pacman.setOrient(orient);

    }

    public void loadMap(String fileName) {
        iteams.clear(); // Xoa data trong list cu khi goi ham nay 2 lan
        File file = new File(getClass().getResource("/res/maps/" + fileName).getPath());
        int row = 0;
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(file, "r");
            String line;
            while ((line = raf.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) {
                    int type = line.charAt(i) - '0';
                    switch (type) {
                        case Item.TYPE_STONE:
                            iteams.add(new Item(Item.SIZE * i, Item.SIZE * row, Item.TYPE_STONE));
                            break;
                        case Item.TYPE_BEAN_NORMAL:
                            numberOfBeanNomal++;
                            iteams.add(new Item(Item.SIZE * i, Item.SIZE * row, Item.TYPE_BEAN_NORMAL));
                            break;
                        case Item.TYPE_BEAN_POWER:
                            iteams.add(new Item(Item.SIZE * i, Item.SIZE * row, Item.TYPE_BEAN_POWER));
                            break;
                    }
                }
                row++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                raf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public void drawIteam(Graphics2D graphics2D) {
        assert iteams != null;
        for (int i = 0; i < iteams.size(); i++) {
            iteams.get(i).draw(graphics2D);
        }
    }

    public void handleMovePacMan(int count) {
        boolean breackFor = false;

        for (int i = 0; i < iteams.size(); i++) {
            if (pacman.collision(iteams.get(i))) {
                switch (iteams.get(i).getType()) {
                    case Item.TYPE_STONE:
                        return;
                    case Item.TYPE_BEAN_NORMAL:
                        iteams.remove(i);
                        score++;
                        numberOfBeanNomal--;
                        breackFor = true;
                        break;
                    case Item.TYPE_BEAN_POWER:
                        score += 2;
                        break;
                }
                if (breackFor) break;
            }
        }
        Rectangle rePacman = new Rectangle(pacman.getX(), pacman.getY(), pacman.SIZE, Pacman.SIZE);
        for (int i = 0; i < ghosts.size(); i++) {
            if (rePacman.intersects(ghosts.get(i).getReGhost())) {
            }
        }
        movePacman(count);
    }

    public boolean canChangeOrientPacman() {
        int xRec = pacman.getX();
        int yRec = pacman.getY();

        switch (pacmanNextOrient) {
            case Pacman.UP:
                yRec--;
                break;
            case Pacman.DOWN:
                yRec++;
                break;
            case Pacman.LEFT:
                xRec--;
                break;
            case Pacman.RIGHT:
                xRec++;
                break;
            default:
                xRec += 2;
                yRec += 2;
        }
        Rectangle reNextOrient = new Rectangle(xRec, yRec, Pacman.SIZE, Pacman.SIZE);
        for (int i = 0; i < iteams.size(); i++) {
            if (iteams.get(i).getType() == Item.TYPE_STONE && reNextOrient.intersects(iteams.get(i).getRItem())) {
                return false;
            }

        }
        return true;
    }

    public boolean isPacmanChangeOrient() {
        return pacmanNextOrient != pacman.getOrient();

    }


//    public void moveBullet() {
//        for (int i = 0; i < bullets.size(); i++) {
//
//        }
//    }

    public void drawBullet(Graphics2D graphic2D) {
        for (Bullet bullet : bullets) {
            bullet.draw(graphic2D);
        }
    }

    public void fireByPacman() {
        if (score >= 10) {
            if (countDowntFirePacman == 0) {
                score -= 10;
                Bullet bullet = new Bullet(pacman.getOrient(), pacman.getX(), pacman.getY());
                bullets.add(bullet);
                countDowntFirePacman = MAX_COUNTDOWN_PACMAN;
            } else {
                countDowntFirePacman -= 5;
            }

        }
    }

    public void handleBulletMove() {
        boolean isBreak = false;
        int maxI = bullets.size();
        for (int i = 0; i < bullets.size(); i++) {
            isBreak = false;
            bullets.get(i).move();
            Rectangle reBullet = new Rectangle(bullets.get(i).getX(), bullets.get(i).getY(), Bullet.SIZE, Bullet.SIZE);
            for (int j = 0; j < ghosts.size(); j++) {
                if (reBullet.intersects(ghosts.get(j).getReGhost())) {
                    System.out.println("Ghost Die");
                    bullets.remove(i);
                    isBreak = true;
                    break;
                }
            }
            if (isBreak == true) {
                break;
            }
            if (bullets.get(i).getX() < 0 || bullets.get(i).getX() > GUI.WIDTH_FRAME
                    || bullets.get(i).getY() < 0 || bullets.get(i).getY() > GUI.HEIGHT_FRAME) {
                bullets.remove(i);
                maxI = bullets.size();
            }

            for (int j = 0; j < iteams.size(); j++) {

                if (i < bullets.size() && !bullets.isEmpty() && bullets.get(i).collision(iteams.get(j))) { // Phải check lại i vì size bullet đã thay đổi có thể nhỏ hơn i
                    bullets.remove(i);
                    isBreak = true;
                    break;
                }
            }
            if (isBreak == true) {
                break;
            }

        }

    }

    public void drawSwirl(Graphics2D graphics2D) {
        if (numberOfBeanNomal == 0) {
            swirl.draw(graphics2D);
        }
    }

    public boolean isWin() {
        if (numberOfBeanNomal == 0) {
            Rectangle recPacman = new Rectangle(pacman.getX(), pacman.getY(), Pacman.SIZE, Pacman.SIZE);
            if (recPacman.intersects(swirl.getrSwirl())) {
                return true;
            }
        }
        return false;
    }

    private void initializeGhost() {
        ghosts = new ArrayList<>();
        ghosts.add(new Ghost((NUM_OF_COLUMNS_MAP / 2) * Item.SIZE, (NUM_OF_ROWS_MAP / 2 - 3) * Item.SIZE, Ghost.TYPE_BLINKY, 1, Ghost.FOUR_ORIENTS));
        ghosts.add(new Ghost((NUM_OF_COLUMNS_MAP / 2 - 1) * Item.SIZE, (NUM_OF_ROWS_MAP / 2 - 3) * Item.SIZE, Ghost.TYPE_PINKY, 1, Ghost.FOUR_ORIENTS));
        ghosts.add(new Ghost((NUM_OF_COLUMNS_MAP / 2 + 1) * Item.SIZE, (NUM_OF_ROWS_MAP / 2 - 3) * Item.SIZE, Ghost.TYPE_CLYDE, 1, Ghost.FOUR_ORIENTS));
        ghosts.add(new Ghost((NUM_OF_COLUMNS_MAP / 2) * Item.SIZE, (NUM_OF_ROWS_MAP / 2 - 3) * Item.SIZE, Ghost.TYPE_INKY, 1, Ghost.LEFT_RIGHT));
    }

    public void drawGhost(Graphics2D graphics2D) {
        for (int i = 0; i < ghosts.size(); i++) {
            ghosts.get(i).draw(graphics2D);
        }
    }

//    public void moveGhost(int count) {
//        for (int i = 0; i < ghosts.size(); i++) {
//            ghosts.get(i).move(count);
//        }
//    }

    public void handleGhostMove(int count) {
        boolean[] isMove = new boolean[4];
        for (int j = 0; j < ghosts.size(); j++) {
            for (int i = 0; i < iteams.size(); i++) {
                if (ghosts.get(j).collision(iteams.get(i))) {
                    isMove[j] = false;
                    break;
                } else {
                    isMove[j] = true;
                }
            }
            if (isMove[j] == true) {
                ghosts.get(j).move(count);
            } else {
                ghosts.get(j).autoChaneOrient();
            }
        }
    }
}
