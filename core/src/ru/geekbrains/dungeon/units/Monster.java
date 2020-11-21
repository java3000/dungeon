package ru.geekbrains.dungeon.units;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import ru.geekbrains.dungeon.GameController;

public class Monster extends Unit {
    private float aiBrainsImplseTime;
    private Unit target;
    //2
    private final int hitAreaCells;
    private final Rectangle hitArea;

    public Monster(TextureAtlas atlas, GameController gc) {
        super(gc, 5, 2, 10);
        this.texture = atlas.findRegion("monster");
        this.textureHp = atlas.findRegion("hp");
        this.hp = -1;
        //2
        this.hitAreaCells = 5;
        this.hitArea = new Rectangle();
        hitArea.height = hitArea.width = gc.getGameMap().getCellSize() * hitAreaCells;
    }

    public void activate(int cellX, int cellY) {
        this.cellX = cellX;
        this.cellY = cellY;
        this.targetX = cellX;
        this.targetY = cellY;
        this.hpMax = 10;
        this.hp = hpMax;
        this.target = gc.getUnitController().getHero();
        //2
        this.hitArea.x = this.cellX * gc.getGameMap().getCellSize();
        this.hitArea.y = this.cellY * gc.getGameMap().getCellSize();
    }

    public void update(float dt) {
        super.update(dt);
        if (canIMakeAction()) {
            if (isStayStill()) {
                aiBrainsImplseTime += dt;
            }
            if (aiBrainsImplseTime > 0.4f) {
                aiBrainsImplseTime = 0.0f;
                if (canIAttackThisTarget(target)) {
                    attack(target);
                } else {
                    tryToMove();
                }
            }
        }
        //2
        hitArea.x = (this.cellX - Math.abs(hitAreaCells / 2)) * gc.getGameMap().getCellSize();
        hitArea.y = (this.cellY - Math.abs(hitAreaCells / 2)) * gc.getGameMap().getCellSize();
    }

    public void tryToMove() {
        int bestX = -1, bestY = -1;
        float bestDst = 10000;

        //2
        // 2. Монстры охотятся за героем, только если он находится в радиусе N клеток (пусть 5),
        if (hitArea.contains(target.getCellX() * gc.getGameMap().getCellSize(), target.getCellY() * gc.getGameMap().getCellSize())) {
            for (int i = cellX - 1; i <= cellX + 1; i++) {
                for (int j = cellY - 1; j <= cellY + 1; j++) {
                    if (Math.abs(cellX - i) + Math.abs(cellY - j) == 1 && gc.getGameMap().isCellPassable(i, j) && gc.getUnitController().isCellFree(i, j)) {
                        float dst = (float) Math.sqrt((i - target.getCellX()) * (i - target.getCellX()) + (j - target.getCellY()) * (j - target.getCellY()));
                        if (dst < bestDst) {
                            bestDst = dst;
                            bestX = i;
                            bestY = j;
                        }
                    }
                }
            }
            // в противном случае, бегают на случайную клетку
        } else {
            for (int i = cellX - 1; i <= cellX + 1; i++) {
                for (int j = cellY - 1; j <= cellY + 1; j++) {
                    if (Math.abs(cellX - i) + Math.abs(cellY - j) == 1 && gc.getGameMap().isCellPassable(i, j) && gc.getUnitController().isCellFree(i, j)) {
                        bestX = i;
                        bestY = j;
                    }
                }
            }
        }
        goTo(bestX, bestY);
    }

    //вспомогательный метод, чтобы видеть радиус охоты на персонажа
/*    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);
        batch.end();
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0, 1, 0, 1);
        shapeRenderer.rect(hitArea.x, hitArea.y, hitArea.width, hitArea.height);
        shapeRenderer.end();
        batch.begin();
    }*/
}
