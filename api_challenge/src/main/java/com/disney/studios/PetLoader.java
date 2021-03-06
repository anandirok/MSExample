package com.disney.studios;

import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.slf4j.LoggerFactory.getLogger;

@Component
public class PetLoader implements InitializingBean {

    private static final Logger LOGGER = getLogger(PetLoader.class);

    // Resources to the different files we need to load.
    @Value("classpath:/data/labrador.txt")
    private Resource labradors;

    @Value("classpath:/data/pug.txt")
    private Resource pugs;

    @Value("classpath:/data/retriever.txt")
    private Resource retrievers;

    public Resource getLabradors() {
		return labradors;
	}

	public void setLabradors(Resource labradors) {
		this.labradors = labradors;
	}

	public Resource getPugs() {
		return pugs;
	}

	public void setPugs(Resource pugs) {
		this.pugs = pugs;
	}

	public Resource getRetrievers() {
		return retrievers;
	}

	public void setRetrievers(Resource retrievers) {
		this.retrievers = retrievers;
	}

	public Resource getYorkies() {
		return yorkies;
	}

	public void setYorkies(Resource yorkies) {
		this.yorkies = yorkies;
	}

	@Value("classpath:/data/yorkie.txt")
    private Resource yorkies;

    @Autowired
    DataSource dataSource;

    /**
     * Load the different breeds into the data source after
     * the application is ready.
     *
     * @throws Exception In case something goes wrong while we load the breeds.
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        loadBreed("Labrador", labradors);
        loadBreed("Pug", pugs);
        loadBreed("Retriever", retrievers);
        loadBreed("Yorkie", yorkies);
    }

    /**
     * Reads the list of dogs in a category and (eventually) add
     * them to the data source.
     * @param breed The breed that we are loading.
     * @param source The file holding the breeds.
     * @throws IOException In case things go horribly, horribly wrong.
     */
    private void loadBreed(String breed, Resource source) throws IOException {
        LOGGER.debug("Loading breed {}", breed);
        try ( BufferedReader br = new BufferedReader(new InputStreamReader(source.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                LOGGER.debug(line);
                // TODO: Create appropriate objects and save them to the datasource.
            }
        }
    }
}
