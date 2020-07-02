package corp.gruposfa.novo.mapper;

import org.mapstruct.Mapper;

import corp.gruposfa.novo.model.NotaFiscal;
import corp.gruposfa.novo.model.dto.NotaFiscalDTO;

@Mapper(componentModel = "spring")
public interface NotaFiscalMapper {
	
	NotaFiscal toEntity(NotaFiscalDTO dto);
}
