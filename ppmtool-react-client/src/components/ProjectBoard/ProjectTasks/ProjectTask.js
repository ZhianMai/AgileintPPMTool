import React, { Component } from "react";

class ProjectTask extends Component {
  render() {
    const { project_task } = this.props;
    let priorityString;
    let priorityClass;

    switch (project_task.priority) {
      case 1:
        priorityClass = "bg-danger text-light";
        priorityString = "HIGH";
        break;
      case 2:
        priorityClass = "bg-warning text-light";
        priorityString = "MEDIUM";
        break;
      case 3:
        priorityClass = "bg-info text-light";
        priorityString = "LOW";
        break;
    }

    return (
      <div className="card mb-1 bg-light">
        <div className={`card-header text-primary ${priorityClass}`}>
          ID: {project_task.projectSequence} -- Priority: {priorityString}
        </div>
        <div className="card-body bg-light">
          <h5 className="card-title">{project_task.summary}</h5>
          <p className="card-text text-truncate ">
            {project_task.acceptanceCriteria}
          </p>
          <a href="" className="btn btn-primary">
            View / Update
          </a>

          <button className="btn btn-danger ml-4">Delete</button>
        </div>
      </div>
    );
  }
}
export default ProjectTask;
