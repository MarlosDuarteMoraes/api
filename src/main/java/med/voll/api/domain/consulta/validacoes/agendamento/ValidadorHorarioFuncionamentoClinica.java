package med.voll.api.domain.consulta.validacoes.agendamento;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoDeConsulta {

	public void validar(DadosAgendamentoConsulta dados) {
		
		LocalDateTime dataConsulta = dados.data();
		
		boolean domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
		
		boolean antesDaAberturaDaClinica = dataConsulta.getHour() < 7;
		
		boolean depoisDoEncerramentoDaClinica = dataConsulta.getHour() > 18;
		
		if(domingo || antesDaAberturaDaClinica || depoisDoEncerramentoDaClinica) {
			throw new ValidacaoException("Consulta fora do horário de funcionamento da clinica");
		}
			
	}
}
