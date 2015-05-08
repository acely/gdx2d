package hevs.gdx2d.demos.tilemap;

import hevs.gdx2d.lib.GdxGraphics;
import hevs.gdx2d.lib.PortableApplication;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * How to load .tmx resources generated by the
 * Tiled editor (http://www.mapeditor.org/)
 *
 * @author Pierre-André Mudry (mui)
 * @version 0.2
 */
public class DemoTile extends PortableApplication {

	TiledMap tiledMap;
	TiledMapRenderer tiledMapRenderer;
	
	float zoom = 1f;

	Vector2 position = new Vector2(0,0);
	
	@Override
	public void onInit() {
		setTitle("Tile maps loader, mui 2015");

		Gdx.app.log("[TileDemo]", "Demonstrates loading a generated map");

		tiledMap = new TmxMapLoader().load("data/maps/desert.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
	}

	@Override
	public void onGraphicRender(GdxGraphics g) {
		g.clear();
		g.zoom(zoom);
		g.moveCamera(position.x, position.y);
		
		tiledMapRenderer.setView(g.getCamera());	
		tiledMapRenderer.render();
		
		g.drawSchoolLogo();
		g.drawFPS();
	}

	@Override
	public void onScroll(int amount) {
		super.onScroll(amount);
		if(amount > 0)
			zoom += 0.1;
		else
			zoom -= 0.1;
	}
	
	@Override
	public void onPan(float x, float y, float deltaX, float deltaY) {
		super.onPan(x, y, deltaX, deltaY);
		position.add(-deltaX, deltaY);
	}

	@Override
	public void onKeyDown(int keycode) {
		super.onKeyDown(keycode);
		
		switch (keycode) {
		case Input.Keys.UP:
			position.add(0, -32);
			break;

		case Input.Keys.DOWN:
			position.add(0, 32);
			break;

		case Input.Keys.LEFT:
			position.add(32, 0);
			break;
			
		case Input.Keys.RIGHT:
			position.add(-32, 0);
			break;
			
		default:
			break;
		}
	}

	public static void main(String[] args) {
		new DemoTile();
	}
}
