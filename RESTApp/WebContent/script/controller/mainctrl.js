(function(){
	angular.module('restApp').controller('MainController', MainController);
	MainController.$inject=['$http'];
	function MainController($http){
		var mctrl = this;
		mctrl.people = [];
		mctrl.getAllEmp = function(){
			$http({
				method:'GET',
				url:'api/employee/all'
			}).success(function(data){
				console.log(data);
				mctrl.people = data.payload;
			}).error(function(error){
				console.log(error);
			});
		};
		
		mctrl.getEmp = function(){
			if(mctrl.empid){
				$http({
					method:'GET',
					url:'api/employee/get/' + mctrl.empid
				}).success(function(data){
					console.log(data);
				}).error(function(error){
					console.log(error);
				});
			}
			
		};
		
		mctrl.deleteEmployee = function(){
			if(mctrl.delid){
				$http({
					method:'GET',
					url:'api/employee/del/' + mctrl.delid
				}).success(function(data){
					console.log(data);
				}).error(function(error){
					console.log(error);
				});
			}
		};
		
		mctrl.addEmployee = function(){
			$http({
				method:'POST',
				url:'api/employee/add',
				data: mctrl.newEmp
			}).success(function(data){
				console.log(data);
				mctrl.people.push(mctrl.newEmp);
			}).error(function(error){
				console.log(error);
			});
		};
	}
})();