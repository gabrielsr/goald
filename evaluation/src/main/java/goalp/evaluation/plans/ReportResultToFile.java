package goalp.evaluation.plans;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.inject.Named;

import goalp.Conf;
import goalp.Conf.Keys;
import goalp.evaluation.goals.IReportResult;
import goalp.evaluation.model.Experiment;

@Named
public class ReportResultToFile implements IReportResult {


	@Override
	public void exec(List<Experiment> experiments) {
		
		Path path = Paths.get( Conf.get(Keys.RESULT_FILE));
		
		try (BufferedWriter writer = Files.newBufferedWriter(path)) {
			experiments.forEach(( exp) -> {
				experimentToString(writer, exp);
			});
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}
	
	public void experimentToString(BufferedWriter writer, Experiment exp){
		String experimentReport =  exp.getSpecification().numberOfGoals + "," +
				exp.getSpecification().repoSpec + ",";
		try {
			
			writer.write(experimentReport);
			
		}catch(IOException e){
			throw new IllegalStateException(e);
		}
	}
	

}
