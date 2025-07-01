document.addEventListener("DOMContentLoaded", function () {
    jsf.ready(function () {
        var instance = window.jsPlumb.getInstance({
            Connector: ["Flowchart"],
            PaintStyle: { stroke: "#5c96bc", strokeWidth: 2 },
            Endpoint: ["Dot", { radius: 5 }],
            Anchors: ["Right", "Left"]
        });


        // Load task relationships from JSF managed bean
        var tasks = JSON.parse(document.getElementById("taskData").value);

        // Position nodes dynamically (for a simple left-to-right layout)
        let x = 20, y = 50;
        tasks.forEach(task => {
            let taskDiv = document.getElementById("task-" + task.id);
            if (taskDiv) {
                taskDiv.style.left = x + "px";
                taskDiv.style.top = y + "px";
                x += 180; // Move right for next task
                if (x > 800) { x = 20; y += 100; } // Wrap rows if too wide
            }
        });

        // Draw connections based on predecessor-successor relationships
        tasks.forEach(task => {
            if (task.predecessorId) {
                instance.connect({
                    source: "task-" + task.predecessorId,
                    target: "task-" + task.id,
                    overlays: [["Arrow", { width: 10, length: 10, location: 1 }]]
                });
            }
        });
    });
});
