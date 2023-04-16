package no.cantara.realestate.mappingtable;

/**
 * Hello world!
 */
public class Main {

    boolean loadPlugins() {
        return true;
    }
    public static void main(String[] args) {
        Main main = new Main();
        if (main.loadPlugins()) {
            System.out.println("Plugins loaded ok");
        } else {
            System.err.println("Failed to load plugins");
        }
    }
}
