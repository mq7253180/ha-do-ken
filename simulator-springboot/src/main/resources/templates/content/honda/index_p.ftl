<#assign jslist=["/js/common.js", "/js/content/credit.js"]/>
<#assign csslist=[]/>
<#include "/layout_p.ftl"/>
<@layout>
		<!-- 新增模态框（Modal） -->
		<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content" style="width:100%">
					<div class="modal-header">
						<h4 class="modal-title" id="myModalLabel">新增其他币种的合同授信</h4>
						<button type="button" class="close click-close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						客户: 
						<select id="companySel" class="txt-clear">
							<option value="">请选择</option>
							<#list companies as company>
							<option value="${company.id}">${company.cnName}</option>
							</#list>
						</select>
						币种: 
						<select id="currencySel" class="currency-clear">
							<#list currencies as currency>
							<option value="${currency}">${currency}</option>
							</#list>
						</select>
						保证金比例: <input type="text" id="ratioTxt" style="width:15%;text-align:right" class="txt-clear" />%<br/>
						合同授信额度: <input type="text" id="ammountTxt" class="txt-clear" /><br/>
						日放款额度: <input type="text" id="maxLimitOneDayTxt" class="txt-clear" />
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default click-close" data-dismiss="modal">关闭</button>
						<@button type="button" class="btn btn-primary" id="addSubmitBtn" permission="creditAdd">提交</@button>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal -->
		</div>
		<!-- 更新模态框（Modal） -->
		<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content" style="width:100%">
					<div class="modal-header">
						<h4 class="modal-title" id="myModalLabel">更新合同授信</h4>
						<button type="button" class="close click-close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						客户: <label class="html-clear" id="companyLabel2"></label>
						币种: <label class="html-clear" id="currencyLabel2"></label>
						保证金比例: <input type="text" id="ratioTxt2" style="width:15%;text-align:right" class="txt-clear" />%<br/>
						合同授信额度: <label class="html-clear" id="amountLabel2"></label><br/>
						日放款额度: <input type="text" id="maxLimitOneDayTxt2" class="txt-clear" />
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default click-close" data-dismiss="modal">关闭</button>
						<@button type="button" class="btn btn-primary" id="updateSubmitBtn" permission="creditUpdate">提交</@button>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal -->
		</div>
		<input type="hidden" class="txt-clear" id="creditId2" value="" />
		<!-- 更新授信额模态框（Modal） -->
		<div class="modal fade" id="updateAmountModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content" style="width:100%">
					<div class="modal-header">
						<h4 class="modal-title" id="myModalLabel">更新合同授信额</h4>
						<button type="button" class="close click-close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						客户: <label class="html-clear" id="companyLabel3"></label>
						币种: <label class="html-clear" id="currencyLabel3"></label>
						保证金比例: <label class="html-clear" id="ratioLabel3"></label>%<br/>
						合同授信额度: <input type="text" id="amountTxt3" class="txt-clear" /><br/>
						日放款额度: <label class="html-clear" id="maxLimitOneDayLabel3"></label>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default click-close" data-dismiss="modal">关闭</button>
						<@button type="button" class="btn btn-primary" id="updateAmountSubmitBtn" permission="creditUpdateAmount">提交</@button>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal -->
		</div>
		<input type="hidden" class="txt-clear" id="creditId3" value="" />
		<!-- 添加保证金模态框（Modal） -->
		<div class="modal fade" id="addMarginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content" style="width:100%">
					<div class="modal-header">
						<h4 class="modal-title" id="myModalLabel">添加保证金</h4>
						<button type="button" class="close click-close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						币种: 
						<select id="marginCurrencySel" class="currency-clear">
							<#list currencies as currency>
							<option value="${currency}">${currency}</option>
							</#list>
						</select>
						金额: <input type="text" id="marginAmmountTxt" class="txt-clear" />
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default click-close" data-dismiss="modal">关闭</button>
						<@button type="button" class="btn btn-primary" id="addMarginSubmitBtn" permission="creditMarginAdd">提交</@button>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal -->
		</div>
		<input type="hidden" class="txt-clear" id="creditId4" value="" />
		<div class="hk-pg-wrapper">
			<!-- Container -->
				<!-- Title -->
                <div class="hk-pg-header mb-10">
					<div>
						<h4 class="hk-pg-title"><span class="pg-title-icon"><span class="feather-icon"><i data-feather="book"></i></span></span>合同授信</h4>
                    </div>
					<div class="d-flex">
                        <@button id="addBtn" class="btn btn-primary btn-sm" permission="creditAdd">Create New</@button>
                    </div>
                </div>
                <!-- /Title -->
                <div class="row">
                    <div class="col-xl-12">
                        <section class="hk-sec-wrapper">
                            <div class="row">
                                <div class="col-sm">
                                    <div class="table-wrap">
                                        <div class="table-responsive">
                                            <table id="edit_datable_1" class="table table-bordered table-striped mb-0">
                                                <thead>
                                                    <tr align="center">
                                                        <th>客户名称(中文)</th>
                                                        <th>客户名称(英文)</th>
                                                        <th width="5%">币种</th>
                                                        <th width="7%">授信总额度</th>
                                                        <th width="7%">日放款额度</th>
                                                        <th width="7%">保证金比例</th>
                                                        <th width="7%">已使用额度</th>
                                                        <th>生效时间</th>
                                                        <th width="5%">经办</th>
                                                        <th width="15%">操作项</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                <#if credits?exists>
                                                	<#list credits as credit>
                                                    <tr>
                                                        <td><label id="row_company_${credit.id}">${credit.companyCnName}</label></td>
                                                        <td>${credit.companyEnName}</td>
                                                        <td><label id="row_currency_${credit.id}">${credit.currency}</label></td>
                                                        <td align="right"><label id="row_amount_${credit.id}">${credit.amount}</label></td>
                                                        <td align="right"><label id="row_maxLimitOneDay_${credit.id}">${credit.maxLimitOneDay}</label></td>
                                                        <td align="right"><label id="row_ratio_${credit.id}">${credit.ratio}</label>%</td>
                                                        <td align="right"></td>
                                                        <td>${credit.validFrom}</td>
                                                        <td>${credit.userName}</td>
                                                        <td align="center">
                                                        	<@a href="javascript:void(0);" value="${credit.id}" class="click-update" permission="creditUpdate">修改</@a>|
                                                        	<@a href="javascript:void(0);" value="${credit.id}" class="click-updateAmount" permission="creditUpdateAmount">修改额度</@a>|
                                                        	<@a href="/credit/view/${credit.id}/${pageSize}/1" permission="viewCreditHistory">查看历史</@a>|
                                                        	<@a href="javascript:void(0);" value="${credit.id}" class="click-addMargin" permission="creditMarginAdd">添加保证金</@a>
                                                        </td>
                                                    </tr>
													</#list>
												</#if>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </section>
                    </div>
                </div>
            <!-- /Container -->
        </div>
</@layout>