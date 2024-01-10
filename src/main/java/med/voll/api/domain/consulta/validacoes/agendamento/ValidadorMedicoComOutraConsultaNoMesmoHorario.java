package med.voll.api.domain.consulta.validacoes.agendamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

@Component
public class ValidadorMedicoComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoDeConsulta {

	@Autowired
	private ConsultaRepository repository;
	
	public void validar(DadosAgendamentoConsulta dados) {
		
		Boolean medicoPosssuiOutraConsultaNoMesmoHorario = repository.existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(dados.idMedico(), dados.data());
		
		if(medicoPosssuiOutraConsultaNoMesmoHorario) {
			throw new ValidacaoException("Médico ja possui outra consulta agendada nesse mesmo horário");
		}
	}
}
