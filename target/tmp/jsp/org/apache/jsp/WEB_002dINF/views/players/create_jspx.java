package org.apache.jsp.WEB_002dINF.views.players;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class create_jspx extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(9);
    _jspx_dependants.add("/WEB-INF/tags/form/create.tagx");
    _jspx_dependants.add("/WEB-INF/tags/util/panel.tagx");
    _jspx_dependants.add("/WEB-INF/tags/form/fields/select.tagx");
    _jspx_dependants.add("/WEB-INF/tags/form/fields/reference.tagx");
    _jspx_dependants.add("/WEB-INF/tags/form/fields/simple.tagx");
    _jspx_dependants.add("/WEB-INF/tags/form/fields/input.tagx");
    _jspx_dependants.add("/WEB-INF/tags/form/fields/datetime.tagx");
    _jspx_dependants.add("/WEB-INF/tags/form/fields/checkbox.tagx");
    _jspx_dependants.add("/WEB-INF/tags/form/dependency.tagx");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("<div version=\"2.0\">");
      if (_jspx_meth_form_create_0(_jspx_page_context))
        return;
      if (_jspx_meth_form_dependency_0(_jspx_page_context))
        return;
      out.write("</div>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_form_create_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  form:create
    org.apache.jsp.tag.web.form.create_tagx _jspx_th_form_create_0 = (_jspx_resourceInjector != null) ? _jspx_resourceInjector.createTagHandlerInstance(org.apache.jsp.tag.web.form.create_tagx.class) : new org.apache.jsp.tag.web.form.create_tagx();
    _jspx_th_form_create_0.setJspContext(_jspx_page_context);
    _jspx_th_form_create_0.setZ("sSSrF7CRFsdyh1CMuC3+9xcHNnU=");
    _jspx_th_form_create_0.setRender((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${empty dependencies}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null));
    _jspx_th_form_create_0.setPath("/players");
    _jspx_th_form_create_0.setModelAttribute("player");
    _jspx_th_form_create_0.setId("fc_org_m4_bgw_domain_Player");
    _jspx_th_form_create_0.setJspBody(new create_jspxHelper( 0, _jspx_page_context, _jspx_th_form_create_0, null));
    _jspx_th_form_create_0.doTag();
    return false;
  }

  private boolean _jspx_meth_field_select_0(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  field:select
    org.apache.jsp.tag.web.form.fields.select_tagx _jspx_th_field_select_0 = (_jspx_resourceInjector != null) ? _jspx_resourceInjector.createTagHandlerInstance(org.apache.jsp.tag.web.form.fields.select_tagx.class) : new org.apache.jsp.tag.web.form.fields.select_tagx();
    _jspx_th_field_select_0.setJspContext(_jspx_page_context);
    _jspx_th_field_select_0.setParent(_jspx_parent);
    _jspx_th_field_select_0.setZ("hglJXtrQ4XVjkC2GQWxDotMIJYI=");
    _jspx_th_field_select_0.setPath("/players");
    _jspx_th_field_select_0.setMultiple(new Boolean(true));
    _jspx_th_field_select_0.setItems((java.util.Collection) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${players}", java.util.Collection.class, (PageContext)_jspx_page_context, null));
    _jspx_th_field_select_0.setItemValue("username");
    _jspx_th_field_select_0.setId("c_org_m4_bgw_domain_Player_players1");
    _jspx_th_field_select_0.setField("players1");
    _jspx_th_field_select_0.doTag();
    return false;
  }

  private boolean _jspx_meth_field_select_1(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  field:select
    org.apache.jsp.tag.web.form.fields.select_tagx _jspx_th_field_select_1 = (_jspx_resourceInjector != null) ? _jspx_resourceInjector.createTagHandlerInstance(org.apache.jsp.tag.web.form.fields.select_tagx.class) : new org.apache.jsp.tag.web.form.fields.select_tagx();
    _jspx_th_field_select_1.setJspContext(_jspx_page_context);
    _jspx_th_field_select_1.setParent(_jspx_parent);
    _jspx_th_field_select_1.setZ("r7BJQKjgE8ze8WL/Kuc7GZTqfAY=");
    _jspx_th_field_select_1.setPath("/languages");
    _jspx_th_field_select_1.setMultiple(new Boolean(true));
    _jspx_th_field_select_1.setItems((java.util.Collection) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${languages}", java.util.Collection.class, (PageContext)_jspx_page_context, null));
    _jspx_th_field_select_1.setItemValue("languageCode");
    _jspx_th_field_select_1.setId("c_org_m4_bgw_domain_Player_languages");
    _jspx_th_field_select_1.setField("languages");
    _jspx_th_field_select_1.doTag();
    return false;
  }

  private boolean _jspx_meth_field_select_2(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  field:select
    org.apache.jsp.tag.web.form.fields.select_tagx _jspx_th_field_select_2 = (_jspx_resourceInjector != null) ? _jspx_resourceInjector.createTagHandlerInstance(org.apache.jsp.tag.web.form.fields.select_tagx.class) : new org.apache.jsp.tag.web.form.fields.select_tagx();
    _jspx_th_field_select_2.setJspContext(_jspx_page_context);
    _jspx_th_field_select_2.setParent(_jspx_parent);
    _jspx_th_field_select_2.setZ("FDrsnRd0TbK4NcGZ7pTo3uxO4MI=");
    _jspx_th_field_select_2.setPath("/players");
    _jspx_th_field_select_2.setMultiple(new Boolean(true));
    _jspx_th_field_select_2.setItems((java.util.Collection) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${players}", java.util.Collection.class, (PageContext)_jspx_page_context, null));
    _jspx_th_field_select_2.setItemValue("username");
    _jspx_th_field_select_2.setId("c_org_m4_bgw_domain_Player_players2");
    _jspx_th_field_select_2.setField("players2");
    _jspx_th_field_select_2.doTag();
    return false;
  }

  private boolean _jspx_meth_field_simple_0(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  field:simple
    org.apache.jsp.tag.web.form.fields.simple_tagx _jspx_th_field_simple_0 = (_jspx_resourceInjector != null) ? _jspx_resourceInjector.createTagHandlerInstance(org.apache.jsp.tag.web.form.fields.simple_tagx.class) : new org.apache.jsp.tag.web.form.fields.simple_tagx();
    _jspx_th_field_simple_0.setJspContext(_jspx_page_context);
    _jspx_th_field_simple_0.setParent(_jspx_parent);
    _jspx_th_field_simple_0.setZ("70K4RlwQ1AmKNW4mpRu9cGyRUjs=");
    _jspx_th_field_simple_0.setMessageCodeAttribute("Achieved");
    _jspx_th_field_simple_0.setMessageCode("entity_reference_not_managed");
    _jspx_th_field_simple_0.setId("c_org_m4_bgw_domain_Player_achieveds");
    _jspx_th_field_simple_0.setField("achieveds");
    _jspx_th_field_simple_0.doTag();
    return false;
  }

  private boolean _jspx_meth_field_simple_1(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  field:simple
    org.apache.jsp.tag.web.form.fields.simple_tagx _jspx_th_field_simple_1 = (_jspx_resourceInjector != null) ? _jspx_resourceInjector.createTagHandlerInstance(org.apache.jsp.tag.web.form.fields.simple_tagx.class) : new org.apache.jsp.tag.web.form.fields.simple_tagx();
    _jspx_th_field_simple_1.setJspContext(_jspx_page_context);
    _jspx_th_field_simple_1.setParent(_jspx_parent);
    _jspx_th_field_simple_1.setZ("c0BOCxaQeL7rBmtmuowxUhfE2WM=");
    _jspx_th_field_simple_1.setMessageCodeAttribute("Boardgame");
    _jspx_th_field_simple_1.setMessageCode("entity_reference_not_managed");
    _jspx_th_field_simple_1.setId("c_org_m4_bgw_domain_Player_boardgames");
    _jspx_th_field_simple_1.setField("boardgames");
    _jspx_th_field_simple_1.doTag();
    return false;
  }

  private boolean _jspx_meth_field_simple_2(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  field:simple
    org.apache.jsp.tag.web.form.fields.simple_tagx _jspx_th_field_simple_2 = (_jspx_resourceInjector != null) ? _jspx_resourceInjector.createTagHandlerInstance(org.apache.jsp.tag.web.form.fields.simple_tagx.class) : new org.apache.jsp.tag.web.form.fields.simple_tagx();
    _jspx_th_field_simple_2.setJspContext(_jspx_page_context);
    _jspx_th_field_simple_2.setParent(_jspx_parent);
    _jspx_th_field_simple_2.setZ("ue+90ev4LDfJGbJBjkGcVyFVwVE=");
    _jspx_th_field_simple_2.setMessageCodeAttribute("Played");
    _jspx_th_field_simple_2.setMessageCode("entity_reference_not_managed");
    _jspx_th_field_simple_2.setId("c_org_m4_bgw_domain_Player_playeds");
    _jspx_th_field_simple_2.setField("playeds");
    _jspx_th_field_simple_2.doTag();
    return false;
  }

  private boolean _jspx_meth_field_select_3(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  field:select
    org.apache.jsp.tag.web.form.fields.select_tagx _jspx_th_field_select_3 = (_jspx_resourceInjector != null) ? _jspx_resourceInjector.createTagHandlerInstance(org.apache.jsp.tag.web.form.fields.select_tagx.class) : new org.apache.jsp.tag.web.form.fields.select_tagx();
    _jspx_th_field_select_3.setJspContext(_jspx_page_context);
    _jspx_th_field_select_3.setParent(_jspx_parent);
    _jspx_th_field_select_3.setZ("LiZHWt//f7bU86YeysRDNVY6C8E=");
    _jspx_th_field_select_3.setPath("/genders");
    _jspx_th_field_select_3.setItems((java.util.Collection) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${genders}", java.util.Collection.class, (PageContext)_jspx_page_context, null));
    _jspx_th_field_select_3.setItemValue("genderId");
    _jspx_th_field_select_3.setId("c_org_m4_bgw_domain_Player_sex");
    _jspx_th_field_select_3.setField("sex");
    _jspx_th_field_select_3.doTag();
    return false;
  }

  private boolean _jspx_meth_field_select_4(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  field:select
    org.apache.jsp.tag.web.form.fields.select_tagx _jspx_th_field_select_4 = (_jspx_resourceInjector != null) ? _jspx_resourceInjector.createTagHandlerInstance(org.apache.jsp.tag.web.form.fields.select_tagx.class) : new org.apache.jsp.tag.web.form.fields.select_tagx();
    _jspx_th_field_select_4.setJspContext(_jspx_page_context);
    _jspx_th_field_select_4.setParent(_jspx_parent);
    _jspx_th_field_select_4.setZ("EpMSIC1GEdcdxh2yr0/w88TIJJU=");
    _jspx_th_field_select_4.setPath("/userlevels");
    _jspx_th_field_select_4.setItems((java.util.Collection) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${userlevels}", java.util.Collection.class, (PageContext)_jspx_page_context, null));
    _jspx_th_field_select_4.setItemValue("levelId");
    _jspx_th_field_select_4.setId("c_org_m4_bgw_domain_Player_levelId");
    _jspx_th_field_select_4.setField("levelId");
    _jspx_th_field_select_4.doTag();
    return false;
  }

  private boolean _jspx_meth_field_select_5(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  field:select
    org.apache.jsp.tag.web.form.fields.select_tagx _jspx_th_field_select_5 = (_jspx_resourceInjector != null) ? _jspx_resourceInjector.createTagHandlerInstance(org.apache.jsp.tag.web.form.fields.select_tagx.class) : new org.apache.jsp.tag.web.form.fields.select_tagx();
    _jspx_th_field_select_5.setJspContext(_jspx_page_context);
    _jspx_th_field_select_5.setParent(_jspx_parent);
    _jspx_th_field_select_5.setZ("v2ILcsnwZ1Zx23X1f7WomcnfbFM=");
    _jspx_th_field_select_5.setPath("/countrys");
    _jspx_th_field_select_5.setItems((java.util.Collection) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${countrys}", java.util.Collection.class, (PageContext)_jspx_page_context, null));
    _jspx_th_field_select_5.setItemValue("isoAlpha2");
    _jspx_th_field_select_5.setId("c_org_m4_bgw_domain_Player_country");
    _jspx_th_field_select_5.setField("country");
    _jspx_th_field_select_5.doTag();
    return false;
  }

  private boolean _jspx_meth_field_input_0(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  field:input
    org.apache.jsp.tag.web.form.fields.input_tagx _jspx_th_field_input_0 = (_jspx_resourceInjector != null) ? _jspx_resourceInjector.createTagHandlerInstance(org.apache.jsp.tag.web.form.fields.input_tagx.class) : new org.apache.jsp.tag.web.form.fields.input_tagx();
    _jspx_th_field_input_0.setJspContext(_jspx_page_context);
    _jspx_th_field_input_0.setParent(_jspx_parent);
    _jspx_th_field_input_0.setZ("DNFGvnfx2MYRHgE0ROYh5oOL/xI=");
    _jspx_th_field_input_0.setValidationMessageCode("field_invalid_email");
    _jspx_th_field_input_0.setId("c_org_m4_bgw_domain_Player_email");
    _jspx_th_field_input_0.setField("email");
    _jspx_th_field_input_0.doTag();
    return false;
  }

  private boolean _jspx_meth_field_input_1(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  field:input
    org.apache.jsp.tag.web.form.fields.input_tagx _jspx_th_field_input_1 = (_jspx_resourceInjector != null) ? _jspx_resourceInjector.createTagHandlerInstance(org.apache.jsp.tag.web.form.fields.input_tagx.class) : new org.apache.jsp.tag.web.form.fields.input_tagx();
    _jspx_th_field_input_1.setJspContext(_jspx_page_context);
    _jspx_th_field_input_1.setParent(_jspx_parent);
    _jspx_th_field_input_1.setZ("4XqkcskjGW97vFqRvdAn1TvpDH4=");
    _jspx_th_field_input_1.setRequired(new Boolean(true));
    _jspx_th_field_input_1.setId("c_org_m4_bgw_domain_Player_password");
    _jspx_th_field_input_1.setField("password");
    _jspx_th_field_input_1.doTag();
    return false;
  }

  private boolean _jspx_meth_field_datetime_0(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  field:datetime
    org.apache.jsp.tag.web.form.fields.datetime_tagx _jspx_th_field_datetime_0 = (_jspx_resourceInjector != null) ? _jspx_resourceInjector.createTagHandlerInstance(org.apache.jsp.tag.web.form.fields.datetime_tagx.class) : new org.apache.jsp.tag.web.form.fields.datetime_tagx();
    _jspx_th_field_datetime_0.setJspContext(_jspx_page_context);
    _jspx_th_field_datetime_0.setParent(_jspx_parent);
    _jspx_th_field_datetime_0.setZ("/AmoxWGFV/mB1nhAIj1G5xTJOSI=");
    _jspx_th_field_datetime_0.setRequired(new Boolean(true));
    _jspx_th_field_datetime_0.setId("c_org_m4_bgw_domain_Player_registrationDtm");
    _jspx_th_field_datetime_0.setField("registrationDtm");
    _jspx_th_field_datetime_0.setDateTimePattern((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${player_registrationdtm_date_format}", java.lang.String.class, (PageContext)_jspx_page_context, null));
    _jspx_th_field_datetime_0.doTag();
    return false;
  }

  private boolean _jspx_meth_field_datetime_1(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  field:datetime
    org.apache.jsp.tag.web.form.fields.datetime_tagx _jspx_th_field_datetime_1 = (_jspx_resourceInjector != null) ? _jspx_resourceInjector.createTagHandlerInstance(org.apache.jsp.tag.web.form.fields.datetime_tagx.class) : new org.apache.jsp.tag.web.form.fields.datetime_tagx();
    _jspx_th_field_datetime_1.setJspContext(_jspx_page_context);
    _jspx_th_field_datetime_1.setParent(_jspx_parent);
    _jspx_th_field_datetime_1.setZ("iF3iU+JKQm1Jz98YKg6iP2yDdyk=");
    _jspx_th_field_datetime_1.setId("c_org_m4_bgw_domain_Player_birthDate");
    _jspx_th_field_datetime_1.setField("birthDate");
    _jspx_th_field_datetime_1.setDateTimePattern((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${player_birthdate_date_format}", java.lang.String.class, (PageContext)_jspx_page_context, null));
    _jspx_th_field_datetime_1.doTag();
    return false;
  }

  private boolean _jspx_meth_field_input_2(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  field:input
    org.apache.jsp.tag.web.form.fields.input_tagx _jspx_th_field_input_2 = (_jspx_resourceInjector != null) ? _jspx_resourceInjector.createTagHandlerInstance(org.apache.jsp.tag.web.form.fields.input_tagx.class) : new org.apache.jsp.tag.web.form.fields.input_tagx();
    _jspx_th_field_input_2.setJspContext(_jspx_page_context);
    _jspx_th_field_input_2.setParent(_jspx_parent);
    _jspx_th_field_input_2.setZ("RnrrRlnB1YuXKHFpAMiXwocGPes=");
    _jspx_th_field_input_2.setId("c_org_m4_bgw_domain_Player_bio");
    _jspx_th_field_input_2.setField("bio");
    _jspx_th_field_input_2.doTag();
    return false;
  }

  private boolean _jspx_meth_field_checkbox_0(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  field:checkbox
    org.apache.jsp.tag.web.form.fields.checkbox_tagx _jspx_th_field_checkbox_0 = (_jspx_resourceInjector != null) ? _jspx_resourceInjector.createTagHandlerInstance(org.apache.jsp.tag.web.form.fields.checkbox_tagx.class) : new org.apache.jsp.tag.web.form.fields.checkbox_tagx();
    _jspx_th_field_checkbox_0.setJspContext(_jspx_page_context);
    _jspx_th_field_checkbox_0.setParent(_jspx_parent);
    _jspx_th_field_checkbox_0.setZ("Lo/Hu96KNmic/Xh0fkUAvsh57kI=");
    _jspx_th_field_checkbox_0.setId("c_org_m4_bgw_domain_Player_isPremium");
    _jspx_th_field_checkbox_0.setField("isPremium");
    _jspx_th_field_checkbox_0.doTag();
    return false;
  }

  private boolean _jspx_meth_form_dependency_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  form:dependency
    org.apache.jsp.tag.web.form.dependency_tagx _jspx_th_form_dependency_0 = (_jspx_resourceInjector != null) ? _jspx_resourceInjector.createTagHandlerInstance(org.apache.jsp.tag.web.form.dependency_tagx.class) : new org.apache.jsp.tag.web.form.dependency_tagx();
    _jspx_th_form_dependency_0.setJspContext(_jspx_page_context);
    _jspx_th_form_dependency_0.setZ("OpwaRAB+ilBKqNUoO8Gc880j1Uc=");
    _jspx_th_form_dependency_0.setRender((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${not empty dependencies}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null));
    _jspx_th_form_dependency_0.setId("d_org_m4_bgw_domain_Player");
    _jspx_th_form_dependency_0.setDependencies((java.util.Collection) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${dependencies}", java.util.Collection.class, (PageContext)_jspx_page_context, null));
    _jspx_th_form_dependency_0.doTag();
    return false;
  }

  private class create_jspxHelper
      extends org.apache.jasper.runtime.JspFragmentHelper
  {
    private javax.servlet.jsp.tagext.JspTag _jspx_parent;
    private int[] _jspx_push_body_count;

    public create_jspxHelper( int discriminator, JspContext jspContext, javax.servlet.jsp.tagext.JspTag _jspx_parent, int[] _jspx_push_body_count ) {
      super( discriminator, jspContext, _jspx_parent );
      this._jspx_parent = _jspx_parent;
      this._jspx_push_body_count = _jspx_push_body_count;
    }
    public boolean invoke0( JspWriter out ) 
      throws Throwable
    {
      if (_jspx_meth_field_select_0((javax.servlet.jsp.tagext.JspTag) _jspx_parent, _jspx_page_context))
        return true;
      if (_jspx_meth_field_select_1((javax.servlet.jsp.tagext.JspTag) _jspx_parent, _jspx_page_context))
        return true;
      if (_jspx_meth_field_select_2((javax.servlet.jsp.tagext.JspTag) _jspx_parent, _jspx_page_context))
        return true;
      if (_jspx_meth_field_simple_0((javax.servlet.jsp.tagext.JspTag) _jspx_parent, _jspx_page_context))
        return true;
      if (_jspx_meth_field_simple_1((javax.servlet.jsp.tagext.JspTag) _jspx_parent, _jspx_page_context))
        return true;
      if (_jspx_meth_field_simple_2((javax.servlet.jsp.tagext.JspTag) _jspx_parent, _jspx_page_context))
        return true;
      if (_jspx_meth_field_select_3((javax.servlet.jsp.tagext.JspTag) _jspx_parent, _jspx_page_context))
        return true;
      if (_jspx_meth_field_select_4((javax.servlet.jsp.tagext.JspTag) _jspx_parent, _jspx_page_context))
        return true;
      if (_jspx_meth_field_select_5((javax.servlet.jsp.tagext.JspTag) _jspx_parent, _jspx_page_context))
        return true;
      if (_jspx_meth_field_input_0((javax.servlet.jsp.tagext.JspTag) _jspx_parent, _jspx_page_context))
        return true;
      if (_jspx_meth_field_input_1((javax.servlet.jsp.tagext.JspTag) _jspx_parent, _jspx_page_context))
        return true;
      if (_jspx_meth_field_datetime_0((javax.servlet.jsp.tagext.JspTag) _jspx_parent, _jspx_page_context))
        return true;
      if (_jspx_meth_field_datetime_1((javax.servlet.jsp.tagext.JspTag) _jspx_parent, _jspx_page_context))
        return true;
      if (_jspx_meth_field_input_2((javax.servlet.jsp.tagext.JspTag) _jspx_parent, _jspx_page_context))
        return true;
      if (_jspx_meth_field_checkbox_0((javax.servlet.jsp.tagext.JspTag) _jspx_parent, _jspx_page_context))
        return true;
      return false;
    }
    public void invoke( java.io.Writer writer )
      throws JspException
    {
      JspWriter out = null;
      if( writer != null ) {
        out = this.jspContext.pushBody(writer);
      } else {
        out = this.jspContext.getOut();
      }
      try {
        switch( this.discriminator ) {
          case 0:
            invoke0( out );
            break;
        }
      }
      catch( Throwable e ) {
        if (e instanceof SkipPageException)
            throw (SkipPageException) e;
        throw new JspException( e );
      }
      finally {
        if( writer != null ) {
          this.jspContext.popBody();
        }
      }
    }
  }
}
