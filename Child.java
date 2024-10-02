public class Child {
    private int age;
    private float weight;
    private float height;
    private GuardianAccount guardianAccount;
    public Child(int age, float weight, float height, GuardianAccount guardianAccount) {
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.guardianAccount = guardianAccount;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public GuardianAccount getGuardian() {
        return guardianAccount;
    }

    public void setGuardian(GuardianAccount guardianAccount) {
        this.guardianAccount = guardianAccount;
    }
}
