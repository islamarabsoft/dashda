/**
 * 
 */
package com.dashda.scheduled;

/**
 * @author mhanafy
 *
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import com.dashda.service.components.VisitService;

@Component
@PropertySource("classpath:app-attribute.properties")
public class DiscardVisit{

    private static final Logger log = LoggerFactory.getLogger(DiscardVisit.class);
    
    @Value("${DISCRD_VISIT_DURATION_IN_HOURS}")
	protected String DISCRD_VISIT_DURATION_IN_HOURS;
    
    
    @Autowired
    VisitService visitService;
    
    @Scheduled(cron ="${DISCARD_VISITS_TIME_PLAN}")
    public void reportCurrentTime() {
        log.info(">>~~~~::::::::::~~~~~~~~~~~~>>> Discard Visits CRON JOB Starting <<<~~~~~~~~~~~~::::::::::~~~~<<");
        
        visitService.discardAllVisitsItemsBeforeHoursDuration(Integer.parseInt(DISCRD_VISIT_DURATION_IN_HOURS));
        
        log.info(">>~~~~::::::::::~~~~~~~~~~~~>>> Discard Visits CRON JOB Finishing <<<~~~~~~~~~~~~::::::::::~~~~<<");
    }
}
