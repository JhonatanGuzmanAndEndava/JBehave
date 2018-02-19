package com.endava.jbehave.test;

import java.util.Arrays;
import java.util.List;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.junit.runner.RunWith;
import org.jbehave.core.io.CodeLocations;


import com.endava.jbehave.steps.IncreaseSteps;

import net.serenitybdd.junit.runners.SerenityRunner;

@RunWith(SerenityRunner.class)
public class IncreaseTest extends JUnitStories {
		 
	@Override
	public Configuration configuration() {
		return new MostUsefulConfiguration()
				.useStoryLoader(new LoadFromClasspath(this.getClass()))
				.useStoryReporterBuilder(new StoryReporterBuilder()
						.withCodeLocation(CodeLocations.codeLocationFromClass(this.getClass()))
						.withDefaultFormats());
					
	}

	@Override
	public InjectableStepsFactory stepsFactory() {
		return new InstanceStepsFactory(configuration(), new IncreaseSteps());
	}

	@Override
	protected List<String> storyPaths() {
		return Arrays.asList("increase.story");
	}

}

