package main.entities.entitiesEnums;

public enum Weapon {
    KUNAI(6,"./Game/Sprites/Weapons/Kunai/main.Main.Entities.Sprite.png","./Game/Sprites/Weapons/Kunai/SpriteInHand.png",5,6,true,1,100,5),
    SAI(10,"./Game/Sprites/Weapons/Sai/main.Main.Entities.Sprite.png","./Game/Sprites/Weapons/Sai/SpriteInHand.png",7,6,false,1,0,0),
    SHURIKEN(3,"./Game/Sprites/Weapons/Shuriken/main.Main.Entities.Sprite.png","./Game/Sprites/Weapons/Shuriken/SpriteInHand.png",5,6,true,2,50,2),
    CLUB(13,"./Game/Sprites/Weapons/Club/main.Main.Entities.Sprite.png","./Game/Sprites/Weapons/Club/SpriteInHand.png",10,6,false,1,0,0),
    KATANA(11,"./Game/Sprites/Weapons/Katana/main.Main.Entities.Sprite.png","./Game/Sprites/Weapons/Katana/SpriteInHand.png",13,6,false,1,0,0),
    ;



    private final int weaponRange;
    private final String weaponSpritePath;
    private final String  weaponSpriteInHandPath;
    private final int weaponDamage;
    private final int rotationAngle;
    public final boolean isProjectile;
    private final int numberOfFrames;
    private final int projectileLifeSpan;
    private final int projectileSpeed;

    Weapon(int weaponRange, String weaponSpritePath, String weaponSpriteInHandPath, int weaponDamage, int rotationAngle, boolean isProjectile, int numberOfFrames, int projectileLifeSpan,int projectileSpeed) {
        this.weaponRange = weaponRange;
        this.weaponSpritePath = weaponSpritePath;
        this.weaponSpriteInHandPath = weaponSpriteInHandPath;
        this.weaponDamage = weaponDamage;
        this.rotationAngle = rotationAngle;
        this.isProjectile = isProjectile;
        this.numberOfFrames = numberOfFrames;
        this.projectileLifeSpan = projectileLifeSpan;
        this.projectileSpeed = projectileSpeed;
    }
    public int getWeaponRange() {
        return weaponRange;
    }

    public String getWeaponSpritePath() {
        return weaponSpritePath;
    }

    public String getWeaponSpriteInHandPath() {
        return weaponSpriteInHandPath;
    }

    public int getWeaponDamage() {
        return weaponDamage;
    }

    public int getRotationAngle() {
        return rotationAngle;
    }

    public int getNumberOfFrames() {
        return numberOfFrames;
    }

    public int getProjectileLifeSpan() {
        return projectileLifeSpan;
    }
}