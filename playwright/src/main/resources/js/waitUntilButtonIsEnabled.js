// Waits until a button does not have the disabled attribute
// noinspection BadExpressionStatementJS
/**
 * Waits for the given node to not be disabled.
 *
 * @param {HTMLButtonElement} node the node
 * @param {{timeout: number}} arg the argument
 * @returns {Promise<void>} the promise resolving when the button is not disabled
 */
(node, {timeout}) => new Promise((resolve, reject) => {
    let counter = timeout * 20;

    function checkDisabled() {
        counter--;
        if (counter === 0) {
            reject(new Error(`Button is still disabled after ${timeout} seconds.`));
            return;
        }

        if (node.disabled && document.contains(node)) {
            setTimeout(checkDisabled, 50);
        } else {
            resolve();
        }
    }

    checkDisabled();
})