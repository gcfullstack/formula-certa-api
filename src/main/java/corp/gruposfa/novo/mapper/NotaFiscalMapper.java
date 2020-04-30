package corp.gruposfa.novo.mapper;

import org.mapstruct.Mapper;

import corp.gruposfa.novo.dto.NotaFiscalDTO;
import corp.gruposfa.novo.model.NotaFiscal;

@Mapper(componentModel = "spring")
public interface NotaFiscalMapper {
	
	NotaFiscal toEntity(NotaFiscalDTO dto);
}
