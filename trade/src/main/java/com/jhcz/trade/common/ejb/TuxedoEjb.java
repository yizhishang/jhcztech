/*package com.jhcz.trade.common.ejb;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import weblogic.wtc.jatmi.Reply;
import weblogic.wtc.jatmi.TPException;
import weblogic.wtc.jatmi.TPServiceInformation;
import weblogic.wtc.jatmi.TypedString;

 

public class TuxedoEjb implements SessionBean {

       private static final long serialVersionUID = 1L;

       static final boolean VERBOSE = true;

       private SessionContext ctx;

       *//**

        * Sets the session context.

        * @param ctx

        *            SessionContext Context for session

        *//*

       public void setSessionContext(SessionContext ctx) {

              this.ctx = ctx;

       }

       *//**

        * This method is required by the EJB Specification, but is not used by this

        * example.

        *

        *//*

       public void ejbActivate() {

       }

       *//**

        * This method is required by the EJB Specification, but is not used by this

        * example.

        *

        *//*

       public void ejbPassivate() {

       }

       *//**

        * This method is required by the EJB Specification, but is not used by this

        * example.

        *

        *//*

       public void ejbRemove() {

       }

       public void ejbCreate() throws CreateException {

       }

       public Reply service(TPServiceInformation mydata) throws TPException {

              TypedString data;

              String lowered;

              TypedString return_data;

              data = (TypedString) mydata.getServiceData();

              log("converting " + data);

              lowered = data.toString().toLowerCase();

              log("converted " + data);

              return_data = new TypedString(lowered);

              mydata.setReplyBuffer(return_data);

              return (mydata);

       }

       private void log(String s) {

              if (VERBOSE) {

                     System.out.println(s);

              }

       }

}*/