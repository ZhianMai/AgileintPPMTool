import React, { Component } from "react";
import CreateProjectButton from "./Project/CreateProjectButton";
import ProjectItem from "./Project/ProjectItem";
import { connect } from "react-redux";
import { getProjects } from "../actions/projectActions";
import PropTypes from "prop-types";

class Dashboard extends Component {
  componentDidMount() {
    this.props.getProjects();
  }

  render() {
    const projectObject = {
      projectName: "projectName Props",
      projectIdentifier: "Props",
      description: "des. from Props",
    };

    const { projectList } = this.props.projectData;

    return (
      <div>
        {/* Dashboard Component (Project Item included) */}
        <div className="projects">
          <div className="container">
            <div className="row">
              <div className="col-md-12">
                <h1 className="display-4 text-center">Projects</h1>
                <br />
                <CreateProjectButton />
                <br />
                <hr />

                {projectList.map((project, index) => (
                  <ProjectItem key={project.id} project={project} />
                ))}
              </div>
            </div>
          </div>
        </div>
        {/* End of Dashboard Component */}
      </div>
    );
  }
}

Dashboard.propTypes = {
  projectData: PropTypes.object.isRequired,
  getProjects: PropTypes.func.isRequired,
};

const mapStateToProps = (state) => ({
  projectData: state.projectData,
});

export default connect(mapStateToProps, { getProjects })(Dashboard);
