const jstreeManager = function() {};

// jstree 기본 설정
var jstreeDefaults = {
    "plugins": ["wholerow", "checkbox", "types"],
    "core": {
        "check_callback": true,
        "themes": {
            "responsive": false
        },
        "data": [] // 초기 데이터는 빈 배열
    },
    "types": {
        "default": {
            "icon": "fa fa-folder text-primary fa-lg"
        },
        "file": {
            "icon": "fa fa-file text-success fa-lg"
        }
    }
};

// jstree 초기화 함수
jstreeManager.prototype.initialize = function(id, options) {
    var treeOptions = $.extend(true, {}, jstreeDefaults, options); // 기본 설정과 옵션 병합
    $("#" + id).jstree(treeOptions);
};

// 데이터 로드 함수
jstreeManager.prototype.loadData = function(id, uri) {
    var self = this;
    $.ajax({
        url: uri,
        method: 'GET',
        dataType: 'json',
        success: function(data) {
            self.initialize(id, { "core": { "data": data } });
        },
        error: function() {
            console.error('Failed to load data.');
        }
    });
};

// 노드 삭제 함수
jstreeManager.prototype.deleteNode = function(id, selectedNodes, uri) {
    var self = this;
    $.ajax({
        url: uri,
        method: 'DELETE',
        contentType: 'application/json',
        data: JSON.stringify(selectedNodes),
        success: function() {
            self.loadData(id, uri); // 삭제 후 데이터 새로 고침
        },
        error: function() {
            console.error('Failed to delete nodes.');
        }
    });
};

// 노드 추가 함수
jstreeManager.prototype.addNode = function(id, nodeData) {
    $("#" + id).jstree().create_node("#", nodeData);
};

// 노드 제거 함수
jstreeManager.prototype.removeNode = function(id, nodeId) {
    $("#" + id).jstree().delete_node(nodeId);
};

// jstree 인스턴스를 전역으로 노출
window.JSTreeCommon = new jstreeManager();
