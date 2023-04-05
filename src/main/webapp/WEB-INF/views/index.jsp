<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>El más Repetido con CRUD</title>
<style>
.nombre.ng-valid {
	background-color: lightgreen;
}

.nombre.ng-dirty.ng-invalid-required {
	background-color: red;
}

.nombre.ng-dirty.ng-invalid-minlength {
	background-color: yellow;
}

.entidad.ng-valid {
	background-color: lightgreen;
}

.entidad.ng-dirty.ng-invalid-required {
	background-color: red;
}

.entidad.ng-dirty.ng-invalid-email {
	background-color: yellow;
}

.municipio.ng-valid {
	background-color: lightgreen;
}

.municipio.ng-dirty.ng-invalid-required {
	background-color: red;
}

.municipio.ng-dirty.ng-invalid-email {
	background-color: yellow;
}
</style>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body ng-app="myApp" class="ng-cloak">
	<div class="generic-container" ng-controller="UserController as ctrl">
		<div class="panel panel-default">
			<div class="panel-heading">
				<span class="lead">Registrar persona</span>
			</div>
			<div class="formcontainer">
				<form ng-submit="ctrl.submit()" name="myForm"
					class="form-horizontal">
					<input type="hidden" ng-model="ctrl.user.id" />
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Nombre</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.user.nombre" name="uname"
									class="nombre form-control input-sm"
									placeholder="Ingresa nombre" required ng-minlength="3" />
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.uname.$error.required">Campo
										requerido</span> <span ng-show="myForm.uname.$error.minlength">Requiere
										minimo 3 caracteres</span> <span ng-show="myForm.uname.$invalid">Campo
										invalido</span>
								</div>
							</div>
						</div>
					</div>


					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Entidad</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.user.entidad" name="uenti"
									class="entidad form-control input-sm"
									placeholder="Ingresa entidad" required ng-minlength="3" />
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.uenti.$error.required">Campo
										requerido</span> <span ng-show="myForm.uenti.$error.minlength">Requiere
										minimo 3 caracteres</span> <span ng-show="myForm.uenti.$invalid">Campo
										invalido </span>
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Municipio</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.user.municipio" name="umuni"
									class="municipio form-control input-sm"
									placeholder="Ingresa municipio" required ng-minlength="3" />
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.umuni.$error.required">Campo
										requerido</span> <span ng-show="myForm.umuni.$error.minlength">Requiere
										minimo 3 caracteres</span> <span ng-show="myForm.umuni.$invalid">Campo
										invalido </span>
								</div>
							</div>
						</div>
					</div>


					<div class="row">
						<div class="form-actions floatRight">
							<input type="submit"
								value="{{!ctrl.user.id ? 'Nuevo' : 'Editar'}}"
								class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
							<button type="button" ng-click="ctrl.reset()"
								class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine"
								onclick="">Limpiar</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<span class="lead">Lista de personas </span>
			</div>
			<div class="tablecontainer">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>ID.</th>
							<th>NOMBRE</th>
							<th>ENTIDAD</th>
							<th>MUNICIPIO</th>
							<th width="20%"></th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="u in ctrl.users">
							<td><span ng-bind="u.id"></span></td>
							<td><span ng-bind="u.nombre"></span></td>
							<td><span ng-bind="u.entidad"></span></td>
							<td><span ng-bind="u.municipio"></span></td>
							<td>
								<button type="button" ng-click="ctrl.edit(u.id)"
									class="btn btn-success custom-width">Editar</button>
								<button type="button" ng-click="ctrl.remove(u.id)"
									class="btn btn-danger custom-width">Eliminar</button>
							</td>
						</tr>
					</tbody>
				</table>

			</div>
		</div>

		<form ng-submit="ctrl.submitRepetido()" name="myForm2">

			<div class="row">
				<div class="form-actions floatRight">
					<input type="submit" value="Saber mas repetido GET"
						class="btn btn-primary btn-sm">
				</div>
			</div>

			Más Repetido: <label ng-bind="ctrl.repetido"></label> <br> Cantidad:
			<label ng-bind="ctrl.numero"></label>
		</form>

		<!--  <form ng-submit="ctrl.submitRepetidoPOST()" name="myForm3">

			<div class="row">
				<div class="form-actions floatRight">
					<input type="submit" value="Saber mas repetido POST"
						class="btn btn-primary btn-sm">
				</div>
			</div>

			Repetido: <label ng-bind="ctrl.repetido2"></label> <br> Cantidad:
			<label ng-bind="ctrl.numero2"></label>
		</form>-->


	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
	<script src="<c:url value='/static/js/app.js' />"></script>
	<script src="<c:url value='/static/js/service/user_service.js' />"></script>
	<script
		src="<c:url value='/static/js/controller/user_controller.js' />"></script>
</body>
</html>