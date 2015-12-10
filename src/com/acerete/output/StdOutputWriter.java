package com.acerete.output;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.acerete.service.ColorType;

public class StdOutputWriter implements OutputWriter {

	// SINGLETON
	public final static OutputWriter INSTANCE = new StdOutputWriter();
	private StdOutputWriter() {}
		
	@Override
	public String writeOutputIntoStdout(List<Output> outputList) {
		
		StringBuilder builder = new StringBuilder();
		
		// Print list sorted by case id
		Collections.sort(outputList, new Comparator<Output>() {

			@Override
			public int compare(Output o1, Output o2) {
				return o1.getCaseId() - o2.getCaseId();
			}
			
		});
		
		for (int i = 0; i < outputList.size(); i++) {
			
			StringBuilder sb = new StringBuilder();
			sb.append("Case #");
			sb.append(i + 1);
			sb.append(": ");
			
			if (!outputList.get(i).hasColors()) {
				sb.append("IMPOSSIBLE");
			}
			else {
				for (ColorType color : outputList.get(i).getColors()) {
					sb.append(color.getId());
					sb.append(" ");
				}
			}
			
			builder.append(sb);
		    builder.append("\r\n");
		}
		
		String result = builder.toString();
		System.out.println(result);
		return result;
	}

}
