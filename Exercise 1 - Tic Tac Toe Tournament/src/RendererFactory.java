/**
 * Represents a Renderer Factory that creates players.
 *
 * @author Gal Kalimi
 */
public class RendererFactory {
    /**
     * Creates a renderer with specified name and size
     * @param rendererName  Name of the new renderer.
     * @param size          Size of the board to render.
     * @return              A new renderer.
     */
    public Renderer buildRenderer(String rendererName, int size){

        Renderer renderer = null;
        switch (rendererName){
            case "none":
                renderer = new VoidRenderer();
                break;
            case "console":
                renderer = new ConsoleRenderer(size);
        }
        return renderer;
    }
}
