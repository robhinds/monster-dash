package io.github.robhinds.utils;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class AudioUtils {

    private static AudioUtils ourInstance = new AudioUtils();
    private static Music music;
    private static Sound jumpSound;
    private static Sound hitSound;
    private static Sound restartSound;

    private AudioUtils() {
    }

    public static AudioUtils getInstance() {
        return ourInstance;
    }

    public Music getMusic() {
        return music;
    }


    public void init() {
        music = Gdx.audio.newMusic(Gdx.files.internal(Constants.GAME_MUSIC));
        music.setLooping(true);
        playMusic();
        jumpSound = createSound(Constants.RUNNER_JUMPING_SOUND);
        hitSound = createSound(Constants.RUNNER_HIT_SOUND);
        restartSound = createSound(Constants.RUNNER_RESTART_SOUND);
    }

    public Sound createSound(String soundFileName) {
        return Gdx.audio.newSound(Gdx.files.internal(soundFileName));
    }

    public void playMusic() {
        music.play();
    }

    public void playSound(Sound sound) {
        sound.play();
    }

    public static void dispose() {
        music.dispose();
        jumpSound.dispose();
        hitSound.dispose();
        restartSound.dispose();
    }

    public Sound getJumpSound() {
        return jumpSound;
    }

    public Sound getHitSound() {
        return hitSound;
    }

    public Sound getRestartSound() {
        return restartSound;
    }
}