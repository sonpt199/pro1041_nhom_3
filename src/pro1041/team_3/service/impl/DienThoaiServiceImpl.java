package pro1041.team_3.service.impl;

import java.util.List;
import pro1041.team_3.domainModel.DienThoai;
import pro1041.team_3.repository.DienThoaiRepository;
import pro1041.team_3.service.DienThoaiService;

/**
 *
 * @author ADMIN
 */
public class DienThoaiServiceImpl implements DienThoaiService{

    private DienThoaiRepository dienThoaiRepository;
    public DienThoaiServiceImpl() {
        this.dienThoaiRepository = new DienThoaiRepository();
    }

    @Override
    public List<DienThoai> getAll() {
        return this.dienThoaiRepository.getAll();
    }
    
}
