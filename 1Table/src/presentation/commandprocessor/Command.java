package presentation.commandprocessor;

import domain.*;
import domain.model.*;

public abstract class Command {
    public GDService gdService;
    public GD gd;
    public int id;
    public abstract void execute();
}
