package com.spiritatlas.blockbunny.handlers;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

/**
 * Contains HashMaps for game resources.
 */
public class Content {

    private HashMap<String, Texture> textures;
    private HashMap<String, Music> music;
    private HashMap<String, Sound> sounds;

    public Content() {
        textures = new HashMap<String, Texture>();
        music = new HashMap<String, Music>();
        sounds = new HashMap<String, Sound>();
    }

    /***********/
    /* Texture */

    /**
     * *******
     */

    public void loadTexture(String path) {
        int slashIndex = path.lastIndexOf('/');
        String key;
        if (slashIndex == -1) {
            key = path.substring(0, path.lastIndexOf('.'));
        } else {
            key = path.substring(slashIndex + 1, path.lastIndexOf('.'));
        }
        loadTexture(path, key);
    }

    public void loadTexture(String path, String key) {
        Texture tex = new Texture(Gdx.files.internal(path));
        textures.put(key, tex);
    }

    public Texture getTexture(String key) {
        return textures.get(key);
    }

    public void removeTexture(String key) {
        Texture tex = textures.get(key);
        if (tex != null) {
            textures.remove(key);
            tex.dispose();
        }
    }

    /*********/
	/* Music */

    /**
     * *****
     */

    public void loadMusic(String path) {
        int slashIndex = path.lastIndexOf('/');
        String key;
        if (slashIndex == -1) {
            key = path.substring(0, path.lastIndexOf('.'));
        } else {
            key = path.substring(slashIndex + 1, path.lastIndexOf('.'));
        }
        loadMusic(path, key);
    }

    public void loadMusic(String path, String key) {
        Music m = Gdx.audio.newMusic(Gdx.files.internal(path));
        music.put(key, m);
    }

    public Music getMusic(String key) {
        return music.get(key);
    }

    public void removeMusic(String key) {
        Music m = music.get(key);
        if (m != null) {
            music.remove(key);
            m.dispose();
        }
    }

    /*******/
	/* SFX */

    /**
     * ***
     */

    public void loadSound(String path) {
        int slashIndex = path.lastIndexOf('/');
        String key;
        if (slashIndex == -1) {
            key = path.substring(0, path.lastIndexOf('.'));
        } else {
            key = path.substring(slashIndex + 1, path.lastIndexOf('.'));
        }
        loadSound(path, key);
    }

    public void loadSound(String path, String key) {
        Sound sound = Gdx.audio.newSound(Gdx.files.internal(path));
        sounds.put(key, sound);
    }

    public Sound getSound(String key) {
        return sounds.get(key);
    }

    public void removeSound(String key) {
        Sound sound = sounds.get(key);
        if (sound != null) {
            sounds.remove(key);
            sound.dispose();
        }
    }

    /*********/
	/* other */

    /**
     * *****
     */

    public void removeAll() {
		/*Iterator<Map.Entry<String, Texture>> iter1 = textures.entrySet().iterator();
		while(iter1.hasNext()) {
			Texture tex = iter1.next().getValue();
			tex.dispose();
			iter1.remove();
		}
		Iterator<Map.Entry<String, Music>> iter2 = music.entrySet().iterator();
		while(iter2.hasNext()) {
			Music music = iter2.next().getValue();
			music.dispose();
			iter2.remove();
		}
		Iterator<Map.Entry<String, Sound>> iter3 = sounds.entrySet().iterator();
		while(iter3.hasNext()) {
			Sound sound = iter3.next().getValue();
			sound.dispose();
			iter3.remove();
		}*/
        for (Object o : textures.values()) {
            Texture tex = (Texture) o;
            tex.dispose();
        }
        textures.clear();
        for (Object o : music.values()) {
            Music music = (Music) o;
            music.dispose();
        }
        music.clear();
        for (Object o : sounds.values()) {
            Sound sound = (Sound) o;
            sound.dispose();
        }
        sounds.clear();
    }

}
