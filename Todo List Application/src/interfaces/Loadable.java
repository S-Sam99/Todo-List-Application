package interfaces;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

public interface Loadable {
    public void load(String s) throws AccessDeniedException, IOException;

}
