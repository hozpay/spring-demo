package util;

public non-sealed class Bass extends FishSealed {
    // non-sealed modifier is used to open a sealed parent class to potentially unknown subclasses.
    // This mean Bass class is now easily extendable by any other general class.
    public String getBassName() {
        return "My name is Bass Fish";
    }

}
