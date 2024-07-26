package presentation.commandprocessor;

import domain.GDService;

public class SumCountCommand extends Command {
    private String loaiNha;
    private String loaiDat;

    public SumCountCommand(GDService gdService, String _loaiNha, String _loaiDat) {
        this.gdService = gdService;
        loaiNha = _loaiNha;
        loaiDat = _loaiDat;
    }

    @Override
    public void execute() {
        if (loaiDat.isEmpty()) {
            this.gdService.sumCountGD(loaiNha);
        } else {
            this.gdService.sumCountGD(loaiDat);
        }
    }
}
